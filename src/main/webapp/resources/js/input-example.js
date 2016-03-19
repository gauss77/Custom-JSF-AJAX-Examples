var inputIsChanged = jsfjs.eventHandlerFactory(function (element, jsUpdate) {
    $(element).toggleClass('changed', jsUpdate === 'true');
});