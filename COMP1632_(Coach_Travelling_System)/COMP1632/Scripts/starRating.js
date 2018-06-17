
$(".rate").hover(function () {
    $(this).addClass('starHighlight');
    $(this).prevAll('.rate').addClass('starHighlight');
}, function () {
    $(this).removeClass('starHighlight');
    $(this).prevAll('.rate').removeClass('starHighlight');
});

$('.rate').click(function (e) {
    $(this).addClass('starSelected');
    $(this).prevAll('.rate').addClass("starSelected");
    $("#Id").val($(".starSelected").length);
});
