$(function(){
    // 화면이 1200px 이하일때 메뉴에 호버시
    let lnbWrap = $('.lnb .lnbWrap');

    lnbWrap.mouseenter(function(){
        $(this).addClass('active');
    });

    lnbWrap.mouseleave(function(){
        $(this).removeClass('active');
    });

    // // 원뎁스 메뉴 클릭시
    // let lnbMenu = $('.lnb .lnbWrap > ul > li > a');
    
    // lnbMenu.click(function(){
    //     lnbMenu.removeClass('on');
    //     $(this).addClass('on');

    //     if ( $(this).siblings('.twoDp').hide() ) {
    //         lnbMenu.siblings('.twoDp').fadeOut();
    //         $(this).siblings('.twoDp').fadeIn();
    //         $('.wrapper').animate({'margin-left':'240px'});
    //     }
    // });

    // let lnbTwoDpWrap = $('.lnb .lnbWrap .twoDp');

    // lnbTwoDpWrap.mouseleave(function(){
    //     lnbTwoDpWrap.hide();
    //     $('.wrapper').animate({'margin-left':'0'});
    //     $(this).siblings('.on').removeClass('on');
    // });

    // 투뎁스 클릭시
    let lnbTwoDpMenu = $('.lnb .lnbWrap .twoDp .twoDpWrap > li > a');
    
    lnbTwoDpMenu.click(function(){
        lnbTwoDpMenu.removeClass('active');
        $(this).addClass('active');
        lnbTwoDpMenu.not(this).siblings('.threeDp').slideUp();
        $(this).siblings('.threeDp').slideDown();
        
    });

    // 투뎁스 다운메뉴 클릭시
    let lnbDownMenuTitle = $('.lnb .lnbWrap .twoDp .downMenu .downMenuTitle');

    lnbDownMenuTitle.click(function(){
        $(this).siblings('.downMenuList').slideToggle();
        $(this).toggleClass('active');
    });

    // 쓰리뎁스 클릭시
    let lnbThreeDp = $('.lnb .lnbWrap .twoDp .twoDpWrap .threeDp > li > a');

    lnbThreeDp.click(function(){
        lnbThreeDp.removeClass('active');
        $(this).addClass('active');
    });
});