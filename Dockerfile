FROM adoptopenjdk/openjdk8:ubi
RUN mkdir -p /var/code

ADD  ./DockerFiless/dev.yml /var/code
ADD  ./target/assignment-1.0-SNAPSHOT.jar /var/code
ADD  ./DockerFiless/entrypoint.sh /var/code
RUN chmod +x /var/code/dev.yml
RUN chmod +x /var/code/assignment-1.0-SNAPSHOT.jar
RUN chmod +x /var/code/entrypoint.sh
ENTRYPOINT ["/var/code/entrypoint.sh"]