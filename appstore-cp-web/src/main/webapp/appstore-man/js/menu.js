$(document).ready(function() {
    //$("div.list").hide();//默认折叠
    $(".box h1").toggle(function() {
        $(this).next(".list").slideToggle("slow");
        jQuery(".box h1 span").attr("class", "menu2")
                .siblings(".menu2")
                .attr("class", "menu1");
    }, function() {
        $(this).next(".list").slideToggle("slow");
        jQuery(".box h1 span").attr("class", "menu1")
                .siblings(".menu1")
                .attr("class", "menu2");
    })
    $(".box h2").toggle(function() {
        $(this).next(".list").slideToggle("slow");
        jQuery(".box h2 span").attr("class", "menu2")
                .siblings(".menu2")
                .attr("class", "menu1");
    }, function() {
        $(this).next(".list").slideToggle("slow");
        jQuery(".box h2 span").attr("class", "menu1")
                .siblings(".menu1")
                .attr("class", "menu2");
    })
    $(".box h3").toggle(function() {
        $(this).next(".list").slideToggle("slow");
        jQuery(".box h3 span").attr("class", "menu2")
                .siblings(".menu2")
                .attr("class", "menu1");
    }, function() {
        $(this).next(".list").slideToggle("slow");
        jQuery(".box h3 span").attr("class", "menu1")
                .siblings(".menu1")
                .attr("class", "menu2");
    })
});