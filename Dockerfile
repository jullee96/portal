FROM redis

RUN mkdir /etc/redis

COPY redis.conf /etc/redis/redis.conf
CMD [ "redis-server", "/etc/redis/redis.conf" ]

