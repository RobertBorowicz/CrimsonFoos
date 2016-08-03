export default class WebApiClient {

    static get(uri) {
        let request = WebApiClient.buildRequest('GET', uri);
        request.onload = () => {
            return request.status === 200
                    ? JSON.parse(request.responseText)
                    : null;
        };
        request.send();
    }

    static put(uri, body) {
        let request = WebApiClient.buildRequest('PUT', uri);
        request.onload = () => {
            return request.status === 200;
        };
        request.send(JSON.stringify(body));
    }

    static post(uri, body) {
        let request = WebApiClient.buildRequest('POST', uri);
        request.onload = () => {
            return request.status === 200;
        };
        request.send(JSON.stringify(body));
    }

    static delete(uri) {
        let request = WebApiClient.buildRequest('DELETE', uri);
        request.onload = () => {
            return request.status === 200;
        };
        request.send();
    }

    static buildRequest(method, uri) {
        let request = new XMLHttpRequest();
        request.open(method, uri, true);
        request.setRequestHeader('Content-Type', 'application/json');
        return request;
    }
}