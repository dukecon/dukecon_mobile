FROM ubuntu:18.04

ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

# Installing packages
RUN    apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
        default-jre-headless- \
        git \
        openjdk-8-jdk \
        sudo \
        unzip \
        wget \
    && rm -rf /var/lib/apt/lists/ /var/cache/apt/*

# Install Android SDK
ENV ANDROID_SDK_VERSION 4333796
ENV ANDROID_SDK_SHA256 92ffee5a1d98d856634e8b71132e8a95d96c83a63fde1099be3d86df3106def9
ENV ANDROID_HOME /opt/android-sdk-linux_86
ENV ANDROID_SDK_ROOT /opt/android-sdk-linux_86

# Add Android commands to PATH
ENV PATH ${PATH}:${ANDROID_SDK_ROOT}/tools:${ANDROID_SDK_ROOT}/tools/bin:${ANDROID_SDK_ROOT}/platform-tools

# speedup
#COPY sdk-tools-linux-${ANDROID_SDK_VERSION}.zip /tmp/android-sdk-tools.zip
RUN    INSTALL_FILE=/tmp/android-sdk-tools.zip \
    && ((test -f "${INSTALL_FILE}" || wget "https://dl.google.com/android/repository/sdk-tools-linux-${ANDROID_SDK_VERSION}.zip" -O "${INSTALL_FILE}") || true) \
    && echo "${ANDROID_SDK_SHA256} ${INSTALL_FILE}" | sha256sum --check \
    && unzip "${INSTALL_FILE}" -d ${ANDROID_SDK_ROOT} \
    && rm -f "${INSTALL_FILE}"

## Install Android NDK
#ENV ANDROID_NDK_VERSION r16b
#ENV ANDROID_NDK  /opt/android-ndk
## speedup
#COPY android-ndk-r16b-linux-x86_64.zip /tmp/android-ndk.zip
#RUN    INSTALL_FILE=/tmp/android-ndk.zip \
#    && ((test -f "${INSTALL_FILE}" || wget "https://dl.google.com/android/repository/android-ndk-${ANDROID_NDK_VERSION}-linux-x86_64.zip" -O "${INSTALL_FILE}") || true) \
#    && unzip "${INSTALL_FILE}" -d /opt \
#    && mv /opt/android-ndk-${ANDROID_NDK_VERSION} ${ANDROID_NDK} \
#    && rm -f "${INSTALL_FILE}"


RUN    mkdir -p /root/.android \
    && touch /root/.android/repositories.cfg \
    && yes | sdkmanager --licenses \
    && sdkmanager --verbose \
        "add-ons;addon-google_apis-google-24" \
        "build-tools;29.0.2" \
        "build-tools;28.0.3" \
        "platform-tools" \
        "platforms;android-28" \
        "platforms;android-29" \
        "extras;android;m2repository" \
        "extras;google;google_play_services" \
        "extras;google;m2repository" \
        "tools" \
    && echo 'Android SDK installed'

# Install Gradle
ENV GRADLE_VERSION=5.6.1
ENV GRADLE_SHA256=f6ea7f48e2823ca7ff8481044b892b24112f5c2c3547d4f423fb9e684c39f710
ENV GRADLE_HASH=805usxkvhgx6e1wbo8o64g0tx

# speedup
#COPY gradle-${GRADLE_VERSION}-all.zip /home/user/.gradle/wrapper/dists/gradle-${GRADLE_VERSION}-all/${GRADLE_HASH}/gradle-${GRADLE_VERSION}-all.zip
RUN    GRADLE_DIR=/home/user/.gradle/wrapper/dists/gradle-${GRADLE_VERSION}-all/${GRADLE_HASH}/ \
    && INSTALL_FILE=${GRADLE_DIR}/gradle-${GRADLE_VERSION}-all.zip \
    && mkdir -p "${GRADLE_DIR}" \
    && ((test -f "${INSTALL_FILE}" || wget "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-all.zip" -O "${INSTALL_FILE}") || true) \
    && unzip "${INSTALL_FILE}" -d "${GRADLE_DIR}" "gradle-${GRADLE_VERSION}/bin/*" "gradle-${GRADLE_VERSION}/lib/*" \
    && touch "${INSTALL_FILE}.ok"

RUN chmod 777 /opt/android-sdk-linux_86

COPY files/. /

CMD ["bash"]

WORKDIR /opt/workspace