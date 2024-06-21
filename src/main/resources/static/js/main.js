$(function(){

    // main-visual
    $("#main-visual").slick({
    
        autoplay : true,
    
        dots: true,
    
        speed : 400 /* 이미지가 슬라이딩시 걸리는 시간 */,
    
        infinite: true,
    
        autoplaySpeed: 5000 /* 이미지가 다른 이미지로 넘어 갈때의 텀 */,
    
        arrows: true,
    
        slidesToShow: 1,
    
        slidesToScroll: 1,
    
        fade: false,
        
      });
    
      // 사용자 리뷰
    
      $(".user-rv-sl").slick({
    
        autoplay : false,
    
        dots: false,
    
        speed : 700 /* 이미지가 슬라이딩시 걸리는 시간 */,
    
        infinite: true,
    
        autoplaySpeed: 5000 /* 이미지가 다른 이미지로 넘어 갈때의 텀 */,
    
        arrows: true,
    
        slidesToShow: 3,
    
        slidesToScroll: 3,
    
        fade: false,
        
      });
    
    // 고객사
    
    $(".client-wrap").slick({
    
      autoplay : true,
    
      dots: true,
    
      speed : 600 /* 이미지가 슬라이딩시 걸리는 시간 */,
    
      infinite: true,
    
      autoplaySpeed: 5000 /* 이미지가 다른 이미지로 넘어 갈때의 텀 */,
    
      arrows: true,
    
      slidesToShow: 3,
    
      slidesToScroll: 6,
    
      fade: false,
      
    });
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    });