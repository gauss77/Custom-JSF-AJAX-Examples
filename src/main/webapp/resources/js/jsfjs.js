window.jsfjs = {
    eventHandlerFactory: function (handler, componentId) {
        return function (event) {
            if (event.status === 'success') {

                var sourceId = componentId ? componentId : event.source.getAttribute('id');

                var jsUpdate = null, htmlUpdate = null;

                var jsupdates = event.responseXML.getElementById('jsupdates');
                if (jsupdates) {
                    jsupdates = jsupdates.childNodes;
                    for (var i = 0; i < jsupdates.length; i++) {
                        var ju = jsupdates[i];
                        if (ju.getAttribute('src') === sourceId) {
                            jsUpdate = ju.innerText || ju.textContent;
                            break;
                        }
                    }
                }

                var htmlupdates = event.responseXML.getElementById('htmlupdates');
                if (htmlupdates) {
                    htmlupdates = htmlupdates.childNodes;
                    for (var i = 0; i < htmlupdates.length; i++) {
                        var hu = htmlupdates[i];
                        if (hu.getAttribute('src') === sourceId) {
                            htmlUpdate = hu;
                            break;
                        }
                    }
                }

                handler(event.source, jsUpdate, htmlUpdate);
            }
        };
    },
    sendAJAX: function (componentId, render, data, onevent) {
        jsf.ajax.request(componentId, null, {
            execute: '@form',
            render: render,
            customJSData: JSON.stringify(data),
            onevent: onevent
        });
    }
};
