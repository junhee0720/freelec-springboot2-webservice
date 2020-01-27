#!/usr/bin/env bash

# 쉬고 있는 profile 찾기 : real1이 사용 중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음
function find_idel_profile() {
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:profile)

    if [ ${RESPONSE_CODE} -ge 400]

    then
      CURRENT_PROFILE=real2
    else
      CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
      IDEL_PROFILE=real2
    else
      IDEL_PROFILE=real1
    fi

    echo "${IDEL_PROFILE}"
}

function find_idel_port() {
    IDEL_PROFILE=$(find_idel_port)

    if[ ${IDEL_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}