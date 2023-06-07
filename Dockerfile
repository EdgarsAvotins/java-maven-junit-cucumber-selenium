FROM --platform=linux/amd64 maven:3.8.6-jdk-11

# Firefox
ARG FIREFOX_VERSION=107.0
RUN apt-get update -qqy \
	&& apt-get -qqy install libgtk-3-0 libx11-xcb1 libdbus-glib-1-2 libxt6 libasound2 \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
	&& wget -q -O /tmp/firefox.tar.bz2 https://download-installer.cdn.mozilla.net/pub/firefox/releases/$FIREFOX_VERSION/linux-x86_64/en-US/firefox-$FIREFOX_VERSION.tar.bz2 \
	&& tar xjf /tmp/firefox.tar.bz2 -C /opt \
	&& rm /tmp/firefox.tar.bz2 \
	&& mv /opt/firefox /opt/firefox-$FIREFOX_VERSION \
	&& ln -s /opt/firefox-$FIREFOX_VERSION/firefox /usr/bin/firefox

# Copy and prepare mvn repo
WORKDIR /app
COPY . /app
RUN mvn compile

#ENTRYPOINT ["mvn"]
#CMD ["test","-e","-Dtest=testRunner.ParallelTestRunner"]