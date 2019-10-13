#!/bin/bash

if [ -z "${USER_UID}" -o -z "${USER_GID}" ]; then
    echo "Getting UID and GID from current directory"
    CURRENT_DIR=$(ls -lnd .)
    USER_UID=$(echo "${CURRENT_DIR}" | cut -f3 -d' ')
    USER_GID=$(echo "${CURRENT_DIR}" | cut -f4 -d' ')
fi

if [ "${USER_UID}" -ne 0 ]; then
    groupadd \
        --gid ${USER_GID} \
        user
    
    useradd \
        --uid ${USER_UID} \
        --gid ${USER_GID} \
        --shell /bin/bash \
        user
fi

echo "Using UID ${USER_UID} and GID ${USER_GID} for execution"

chown --quiet --recursive "${USER_UID}:${USER_GID}" /home/user

if [ -n "${CACHE_DIR}" ]; then
    mkdir -p "${CACHE_DIR}"
    chown "${USER_UID}:${USER_GID}" "${CACHE_DIR}"
    ln -s "${CACHE_DIR}" ~user/.gradle/caches
fi

echo "Executing $@"

sudo --user user --preserve-env --set-home -- "$@"