var themeApp = {
    backToTop: function () {
        $(window).scroll(function () {
            $(this).scrollTop() > 100 ? $("#back-to-top").fadeIn() : $("#back-to-top").fadeOut()
        }), $("#back-to-top").on("click", function (e) {
            return e.preventDefault(), $("html, body").animate({scrollTop: 0}, 1e3), !1
        })
    }, init: function () {
        themeApp.backToTop()
    }
};
$(document).ready(function () {
    themeApp.init()
});