
Two queues...
Uses topic exchange
One queue for "built in" consumer purchasesY
One queue for external  consumer purchasesX


Download and install Erlang..
Download rabbitmq

Start/ALL programs Rabbittmq… Service
         OR
At CMD line : cd to install folder  cd C:\Program Files\rabbitmq server\ rabittmq_server-3.6.1\sbin
then : :  run    .bat  
 
 rabbitmq-plugins enable rabbitmq_management    //for console http://localhost:15672/#/

DEFAULT PASSWORDS do not seem to work:

rabbitmqctl add_user test test 
rabbitmqctl set_user_tags test administrator 
rabbitmqctl set_permissions -p / test ".*" ".*" ".*”
