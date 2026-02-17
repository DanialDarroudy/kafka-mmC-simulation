import http from 'k6/http';
import { sleep } from 'k6';

const arrivalRate = __ENV.ARRIVAL_RATE;

if (!arrivalRate) {
    throw new Error("ARRIVAL_RATE environment variable is required!");
}

const lambda = parseFloat(arrivalRate);

if (lambda <= 0) {
    throw new Error("ARRIVAL_RATE must be > 0");
}

let counter = 1;

function exponential(lambda) {
    return -Math.log(1 - Math.random()) / lambda;
}

export default function () {

    const payload = JSON.stringify({
        payload: `message-${counter}`
    });

    http.post('http://producer:8080/api/v1/messages', payload, {
        headers: { 'Content-Type': 'application/json' },
    });

    counter++;

    const waitTime = exponential(lambda);
    sleep(waitTime);
}