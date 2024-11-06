FROM openjdk:11

EXPOSE 8097

RUN apt-get update && apt-get install -y git && rm -rf /var/lib/apt/lists/* \
    && git clone https://github.com/Heyko12/ISD_3rd_LAB_BIT.git \
    && rm -rf /ISD_3rd_LAB_BIT/task2

WORKDIR /ISD_3rd_LAB_BIT/task1/Consumer

RUN javac Main.java && jar cfe Consumer.jar Main Main.class MiniClass.class && rm -rf Main.* MiniClass.*

WORKDIR /ISD_3rd_LAB_BIT/task1/Producer

RUN javac Main.java && jar cfe Producer.jar Main Main.class MiniClass.class && rm -rf Main.* MiniClass.*

WORKDIR /ISD_3rd_LAB_BIT/task1

RUN rm -rf Dockerfile init.sh

WORKDIR /

CMD [""]