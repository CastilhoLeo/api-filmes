FROM ubuntu:latest
LABEL authors="leonardocastilho"

ENTRYPOINT ["top", "-b"]