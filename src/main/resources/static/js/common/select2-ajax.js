(function ($) {
     //1.定义jquery的扩展方法select2Ajax
    $.fn.select2Ajax = function (options, param) {
//        if (typeof options == 'string') {
//            return $.fn.combobox.methods[options](this, param);
//        }
        //2.将调用时候传过来的参数和default参数合并
        options = $.extend({}, $.fn.select2Ajax.defaults, options || {});
        //3.添加默认值
        var target = $(this);
        //添加默认class select2
        if (!target.hasClass("select2")) target.addClass("select2");
//        target.attr('valuefield', options.valueField);
//        target.attr('textfield', options.textField);
        target.empty();
        if(options.startEmpty){
        	var option = $('<option></option>');
        	option.attr('value', '');
        	option.text(options.placeholder);
        	target.append(option);
        }
        //4.判断用户传过来的参数列表里面是否包含数据data数据集，如果包含，不用发ajax从后台取，否则否送ajax从后台取数据
        if (options.data) {
            init(target, options.data);
        }
        else {
            //var param = {};
            options.onBeforeLoad.call(target, options.param);
            if (!options.url) return;
            $.getJSON(options.url, options.param, function (data) {
                init(target, data);
            });
        }
        function init(target, data) {
            $.each(data, function (i, item) {
                var option = $('<option></option>');
                option.attr('value', item[options.valueField]);
                option.text(item[options.textField]);
                if(options.selectedValue&&options.selectedValue==item[options.valueField]){
                	 option.attr('selected',"selected");
                }
                target.append(option);
            });
            options.onLoadSuccess.call(target);
        }
    }

    //5.如果传过来的是字符串，代表调用方法。
//    $.fn.combobox.methods = {
//        getValue: function (jq) {
//            return jq.val();
//        },
//        setValue: function (jq, param) {
//            jq.val(param);
//        },
//        load: function (jq, url) {
//            $.getJSON(url, function (data) {
//                jq.empty();
//                var option = $('<option></option>');
//                option.attr('value', '');
//                option.text('请选择');
//                jq.append(option);
//                $.each(data, function (i, item) {
//                    var option = $('<option></option>');
//                    option.attr('value', item[jq.attr('valuefield')]);
//                    option.text(item[jq.attr('textfield')]);
//                    jq.append(option);
//                });
//            });
//        }
//    };

    //6.默认参数列表
    $.fn.select2Ajax.defaults = {
        url: null,
        param: null,
        data: null,
        valueField: 'id',
        textField: 'text',
        startEmpty:true,
        placeholder: '---请选择---',
        selectedValue: null,
        onBeforeLoad: function (param) { },
        onLoadSuccess: function () { }
    };
})(jQuery);