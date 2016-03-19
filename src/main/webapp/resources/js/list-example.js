var listIsChanged = jsfjs.eventHandlerFactory(function (element, jsUpdate, htmlUpdate) {
    $(element).closest('li').remove(); // manually remove item
    $(htmlUpdate).find('li').each(function (index, li) {
        // restoring ids
        $('.scrollable-list li:nth-child(' + (index + 1) + ') a').attr('id', $(li).find('a').attr('id'))
    });
}, 'myform:custom-list');