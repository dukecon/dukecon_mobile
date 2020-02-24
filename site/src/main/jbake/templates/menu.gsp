<%
    //tag::map[]
    def nav = [
        title: 'DukeCon',
        entries: [
          'Docs': [
                  'Workshops': 'workshops'
          ],
          'Dateschütz (DE)': 'dsvgo',
          'Imprint (DE)': 'impressum',
        ]
    ]
    //end::map[]
%>
	<!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>index.html">${nav.title}</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
              <% nav.entries.each { key, value -> %>
                <% if (value.class == String.class) { %>
                  <li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>${value}${(value.endsWith('/') || value.startsWith('http'))?'':'.html'}">${key}</a></li>
                <% } else { %>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${key}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <% value.each { it -> %>
                        <li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>${it.value}${(it.value.endsWith('/') || it.value.startsWith('http'))?'':'.html'}">${it.key}</a></li>
                      <% } %>
                    </ul>
                  </li>
                <% } %>
              <% } %>
          </ul>
            <!-- tag::search[] -->
            <form class="navbar-form navbar-right" action="https://google.de/search">
                <div class="form-group">
                    <input type="hidden" name="q" value="site:dukecon.org">
                    <input type="text" name="q" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <!-- end::search[] -->
        </div><!--/.nav-collapse -->
      </div>
    </div>
