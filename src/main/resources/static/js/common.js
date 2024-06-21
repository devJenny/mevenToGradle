// ////////////////////////////////////////////////////////////////////////////
// Utilities
// ////////////////////////////////////////////////////////////////////////////
var commonUtil = commonUtil || {};
/**
 * 브라우저 닫기
 */
commonUtil.closeWindow = function() {

   var _ua = window.navigator.userAgent || window.navigator.vendor || window.opera;

   if (_ua.toLocaleLowerCase().indexOf('kakaotalk') > -1) {
      // 카카오 인앱 브라우저에서 종료시
      try { window.close(); } catch (e) {}
      try { self.close(); } catch (e) {}
      try {
         window.location.href = (/iPad|iPhone|iPod/.test(_ua)) ? 'kakaoweb://closeBrowser' : 'kakaotalk://inappbrowser/close';
      } catch (e) {}
   } else {
      // 카카오 이외의 브라우저에서 종료시
      try { window.close(); } catch (e) {}
      try { self.close(); } catch (e) {}
      alert('자동으로 닫히지 않을 경우 해당 페이지를 수동으로 닫아주세요.');
   }
}

/**
 * 'classes'문자열에서 'prefix'로 시작하는 첫번째 단어를 찾음
 *
 * @param classes String
 * @param prefix String
 */
commonUtil.getWordStartWith = function(classes, prefix) {

   if (!classes) {
      return '';
   }

   var arrayClasses = classes.match(/\S+/g) || [];
   for (var i = 0; i < arrayClasses.length; i++) {
      if (arrayClasses[i].indexOf(prefix) === 0) {
         return arrayClasses[i]; //.replace(prefix, '');
      }
   }

   return '';
};
/**
 * 'value'값을 갖는 첫 번째 키값을 리턴
 *
 * @param obj Object
 * @param value String
 */
commonUtil.getKeyFromValue = function(obj, value) {

   for (var key in obj) {
      if (obj.hasOwnProperty(key)) {
         if (obj[key] === value) {
            return key;
         }
      }
   }

   return null;
};
/**
 * 금지어 블라인드 처리
 *
 * @param text String
 */
commonUtil.blindForbidden = function(text) {

   if (htForbidden && $.cookie && $.cookie('SHOW_FORBIDDEN') !== 'true') {
      for (var i = 0; i < htForbidden.length; i++) {
         var word = htForbidden[i];
         var regex = new RegExp('(' + word + ')', 'g');
         text = text.replace(regex, commonUtil.repeatString('*', word.length));
      }
   }

   return text;
};
/**
 * 금지어 대체 메세지
 *
 * @param text String
 */
commonUtil.alternativeForbidden = function(text) {

   if (htForbidden && htForbiddenAlternative.length > 0) {
      for (var i = 0; i < htForbidden.length; i++) {
         var word = htForbidden[i];
         if (text.indexOf(word) > -1) {
            return htForbiddenAlternative;
         }
      }
   }

   return text;
};
/**
 * 문자 반복 (String.prototype.repeat IE 미지원)
 *
 * @param string String
 * @param count Integer
 */
commonUtil.repeatString = function(string, count) {

   if (count < 1) {
      return '';
   }

   var result = '';
   var num = 0;
   while (num < count ) {
      num += 1;
      result += string;
   }

   return result;
};
/**
 * Sleep
 *
 * @param seconds Integer
 * @param title String
 */
commonUtil.sleep = function(seconds, title) {

   if (title !== undefined) {
      console.info('>>> sleep: ', title);
   }

   var now = new Date().getTime();
   console.time('sleep');
   while (new Date().getTime() < now + seconds * 1000) {
      ;
   }
   console.timeEnd('sleep');
};
/**
 * 레이어 알람
 *
 * @param text String
 * @param seconds Integer
 */
commonUtil.alert = function(text, callback) {
   var seconds = 1300;

   // 알림 영역 없을 경우 추가
   if ($('.layer_alert').length === 0) {
      $('body').append($('<div class="layer_alert"><p class="alert_text"></p></div>'));
   }

   // 기존 함수 있을 경우 실행
   //if (typeof fn_layerMessage !== 'undefined') {
      //fn_layerMessage(text);
   //} else { // 기존 함수 없을 경우
      // 페이드 아웃 중이면 감춤
      if ($('.layer_alert').hasClass('active')) {
         console.debug('active');
         $('.layer_alert').removeClass("active");
      }

      $('.alert_text').html(text.replace(/\n/gi, '<br />'));
      $('.layer_alert').addClass("active");

      setTimeout(function(){
         $('.layer_alert').removeClass("active");
         if(callback){
            callback();
         }
      }, seconds);
};

commonUtil.defaultAlert = function() {
   var text = '서버와의 통신이 원활하지 않습니다.';
   this.alert(text, null);
};

commonUtil.maxCheck = function(obj) {
   if (obj.value.length > obj.maxLength) {
      obj.value = obj.value.slice(0, obj.maxLength)
   }
};

commonUtil.engCheck = function(obj) {
   $(obj).val($(obj).val().replace(/[0-9]|[^\!-z\s]/gi,""));
};


/**
 * Modal
 *
 * @param html HTML STring
 */
commonUtil.modal = function(html) {

   if ($('.modal').is(':animated')) {
      $('.modal').stop().hide();
   }

   $('.modal .body').html(html);
   $('.modal').show();
};
/**
 * 데스크탑 알람
 *
 * @param text String
 * @param title String
 * @param imageUrl String
 */
commonUtil.noti = function(text, title, imageUrl) {

//   if (htBrowserActive) {
//      console.info('BROWSER IS ACTIVE, IGNORE NOTIFICATION');
//      return;
//   }

   var notiOption = {};
   if (text !== undefined) {
      notiOption.body = text;
   }
   if (imageUrl !== undefined) {
      notiOption.icon = imageUrl;
   }
   if (title === undefined) {
      title = '상담채팅';
   }

   commonUtil.notiSound();

   if ('Notification' in window) {
      if (Notification.permission === 'granted') {
         var notification = new Notification(title, notiOption);
      } else if (Notification.permission !== 'denied') {
         Notification.requestPermission(function(permission) {
            if (permission === 'granted') {
               var notification = new Notification(title, notiOption);
            }
         });
      } else {
         commonUtil.alert(text, 5);
         console.info('Denied notification on this site');
      }
   } else {
      commonUtil.alert(text, 5);
   }
};
/**
 * 알림 사운드 재생
 *
 * @param soundName String
 */
commonUtil.notiSound = function(soundName) {

   if (typeof ion === 'undefined') {
      console.debug('=== NO LIBRARY');
      return;
   }

   console.debug('=== PLAY NOTIFICATION SOUND');
   if (soundName === undefined) {
      ion.sound.play('bell_ring');
   } else {
      ion.sound.play(soundName);
   }
};
if (typeof ion !== 'undefined') {
   ion.sound({
      sounds: [
         {name: 'beer_can_opening'},
         {name: 'bell_ring'},
         {name: 'button_tiny'},
      ],
      path: 'audio/',
      preload: true,
      multiplay: true,
      volume: 0.9,
   });
}
/**
 * 디버그 모드 여부
 */
commonUtil.isDebug = function() {

//   return (localStorage.debug &&
//         (localStorage.debug === '*' || localStorage.debug === 'happytalk.io:ht'));
};
/**
 * 프로덕션 프로파일 여부, #debug 요소가 없을 경우 프로덕션으로 간주
 */
commonUtil.isProduction = function() {

   return commonUtil.isProfile('live');
};
/**
 * IE 판별
 */
commonUtil.isProfile = function(profile) {

   var result = false;
   $('#data [name=profile]').each(function() {
      if ($(this).val() === profile) {
         result = true;
         return true;
      }
   });

   return result;
};

/**
 * 모바일 디바이스 판별
 */
commonUtil.isMobile = function() {

   var agent = window.navigator.userAgent.toLowerCase();
   console.debug(agent);
   if (agent.match('iphone')
         || agent.match('android')) {
      return true;
   }

   return false;
};
/**
 * ChatContents > extra 정보
 *
 * @param cont String
 */
commonUtil.getFirstExtra = function(cont) {

   var chatContents = JSON.parse(cont);
   if (chatContents.balloons) {
      for (var i = 0; chatContents.balloons.length > i; i++) {
         var balloon = chatContents.balloons[i];
         for (var j = 0; balloon.sections.length > j; j++) {
            var section = balloon.sections[j];
            if (section.extra) {
               return section.extra;
            }
         }
      }
   }

   return '';
}
/**
 * 박스 사이즈 체크
 */
commonUtil.getBoxText200 = function(boxObj, line) {

   if (line === 6) {
      if (typeof(boxObj.css('height')) !== 'undefined'
         && boxObj.css('height').replace('px', '') * 1 > 500) {
         return true;
      } else {
         return false;
      }
   } else {
      if (typeof(boxObj.css('height')) !== 'undefined'
         && boxObj.css('height').replace('px', '') * 1 > 500) {
         return true;
      } else {
         return false;
      }
   }
}

/**
 * date to string
 */
commonUtil.getDateToString = function(date) {

   var year = date.getFullYear();
   var month = date.getMonth() + 1;
   var date = date.getDate();
   var delimiter = '-';

   month = month > 9 ? month : "0" + month;
   date = date > 9 ? date : "0" + date;
   return year + delimiter + month + delimiter + date;
}
/**
 * 유효성 검증
 */
commonUtil.validate = function($form) {

   // $form
   var data = $form.serializeArray();
   console.debug(data);
   var isValid = true;

   // 'required' 속성이 있는 요소는 필수값으로 간주
   // 빈 값이 아니면 유효
   var requiredList = [];
   $('input[required=required], select[required=required]', $form).each(function() {
      requiredList.push($(this).attr('name'));
   });
   console.debug('requiredList', requiredList);

   for (var i = 0; i < requiredList.length; i++) {
      var requiredItem = requiredList[i];
      var hasValue = false;
      for (var j = 0; j < data.length; j++) {
         var item = data[j];
         if (item.name === requiredItem) {
            hasValue = true;
            if (typeof item.value === 'undefined' || item.value.trim() === '') {
               isValid = false;
            }
            break;
         }
      }

      console.log("hasValue====>"+hasValue);
      if (!hasValue || !isValid) {
         var message = $('[name=' + requiredItem +']', $form).data('valid-required-message');
         console.log("message====>"+message);
         this.alert(message ? message : '필수값이 누락되었습니다.');
         $('[name=' + requiredItem +']', $form).focus();
         return false;
      }
   }

   return isValid;
}

/*
 * 금액 콤마
 */
commonUtil.numberWithCommas = function(x)
{
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

commonUtil.getCookie = function(name) {
   var matches = document.cookie.match(new RegExp(
      "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
      ));
      return matches ? decodeURIComponent(matches[1]) : undefined;
}

commonUtil.setCookie = function(name, value) {
   var updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);
   document.cookie = updatedCookie;

}

/**
 * jQuery 로그
 */
$.fn.log = function() {
   console.info.apply(console, this);
   return this;
};

commonUtil.showLoading = function() {
   commonUtil.isOnLoading('on');
}
commonUtil.hideLoading = function() {
   commonUtil.isOnLoading();
}
commonUtil.isOnLoading = function(on) {
   if(on != undefined){
      $('.loading-bar-body').addClass('on')
   }else{
      $('.loading-bar-body').removeClass('on');
   }
}

/**
 * 파일 업로드
 *
 * @param formData FormData
 * @param chatRoomUid String
 * @param userId String
 * @param callback Function
 */
commonUtil.uploadFile = function(formData ,parentPathName, callback) {
   formData.append('parentPathName', parentPathName);

   var imagePath = null;
   var url = contextPath + "/api/upload";

   $.ajax({
         url: url,
         type: 'post',
         data: formData,
         contentType: false,
         processData: false,
         dataType: 'json'
   }).done(function(data) {
      //로딩바
      if(data[0].resultErrCd == 'ERR'){
        alert(data[0].resultMessage);
      }else{
        callback(data);
      }

   }).fail(function(jqXHR, textStatus, errorThrown) {
      //로딩바
      commonUtil.defaultAlert();
   });
};


/**
 * 파일 다운로드
 *
 * @param formData FormData
 * @param chatRoomUid String
 * @param userId String
 * @param callback Function
 */
commonUtil.downloadFile = function(formData) {
   console.log(formData);
   var url = contextPath + "/api/download";
   formData.submit();

   // 로딩바
   // commonUtil.showLoading();

   // $.ajax({
   //       url: url,
   //       type: 'post',
   //       data: formData,
   //       contentType: false,
   //       processData: false
   // }).done(function(data, status, xhr) {
   //    //로딩바
   //    commonUtil.hideLoading();
   //
   //    if(!data){
   //       commonUtil.alert("파일을 다운로드 할 수 없습니다.<br/>관리자에게 문의하시기 바랍니다.");
   //       return false;
   //    }
   //
   //    // 동작 시도
   //      try {
   //          // 응답의 데이터를 바이너리로 만듦. 타입은 응답 헤더의 Content-Type 참조
   //          var blob = new Blob([data], { type: xhr.getResponseHeader('content-type') });
   //          // getFileName 함수에 Content-Type 헤더 값을 전달하여 파일명 추출
   //          var fileName = getFileName(xhr.getResponseHeader('content-disposition'));
   //          // URL 형태로 전달되었으므로, URI 디코딩 수행
   //          fileName = decodeURI(fileName);
   //
   //          // IE 10일 경우 생성한 blob을 fileName의 이름으로 다운로드 수행
   //          if (window.navigator.msSaveOrOpenBlob) { // IE 10+
   //              window.navigator.msSaveOrOpenBlob(blob, fileName);
   //          }
   //
   //          // IE를 제외한 브라우저일 경우
   //          else { // not IE
   //              // a 태그 동적 생성
   //              var link = document.createElement('a');
   //              // blob 바이너리 파일의 URL 경로 생성
   //              var url = window.URL.createObjectURL(blob);
   //
   //              // 위에서 동적 생성한 a 태그의 URL에 blob 바이너리 파일 URL 기입
   //              link.href = url;
   //              // a 태그 클릭 시 새 창이 아닌 현재창에서 수행
   //              link.target = '_self';
   //
   //              // 파일이름이 유효할 경우, 다운로드 이름을 파일이름으로 기본 지정
   //              if (fileName) link.download = fileName;
   //
   //              // 문서에 설정한 a 태그 생성
   //              document.body.append(link);
   //
   //              // a 태그 클릭 이벤트 (다운로드 실행)
   //              link.click();
   //
   //              // a 태그 삭제
   //              link.remove();
   //
   //              // 생성한 blob 파일의 경로 제거 (다시 요청해도 다운로드 불가)
   //              window.URL.revokeObjectURL(url);
   //          }
   //      } catch (e) {
   //          console.error(e)
   //      }
   // }).fail(function(jqXHR, textStatus, errorThrown) {
   //
   //    //로딩바
   //    commonUtil.hideLoading();
   //
   //    console.error('FAIL REQUEST: UPLOAD FILE: ', textStatus);
   // });

   //로딩바
   // commonUtil.showLoading();
};


/**
 * 파일 다운로드 시 파일명을 가져오기 위한 함수
 */
function getFileName(contentDisposition) {
    var fileName = contentDisposition
        .split(';')
        .filter(function(ele) {
            return ele.indexOf('filename') > -1
        })
        .map(function(ele) {
            return ele
                .replace(/"/g, '')
                .split('=')[1]
        });
    return fileName[0] ? fileName[0] : null
}


/**
 * 한글만 입력가능하도록 체크
 * @param input_value
 *
 * //한글만 입력가능하도록 설정
   $('#korean_only').keyup(function() {
      cm_check_korean($(this));
   });

 */
commonUtil.cm_check_korean = function(fieldObj){
   reg_exp = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;

   if(reg_exp.test(fieldObj.val())) {
      commonUtil.alert("한글만 입력가능합니다.");
      fieldObj.val(fieldObj.val().replace(reg_exp, ''));
   }
}
/**
 *   한글,영문,숫자 만 입력 가능하도록 체크
 */

commonUtil.specialCharacters = function(obj){
   var RegExp = /[^a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]/g;
   if (RegExp.test(obj.value)) {
      commonUtil.alert("특수문자는 입력할 수 없습니다.");
      obj.value = obj.value.replace(RegExp , '');
      return RegExp.test(obj.value);
   }
}
/**
 *   한글,영문만 입력 가능하도록 체크
 */
commonUtil.specialCharactersKoEn = function(obj){
	var RegExp = /[^a-z|A-Z|ㄱ-ㅎ|가-힣]/g;
	if (RegExp.test(obj.value)) {
		commonUtil.alert("한글,영문만 입력할 수 있습니다.");
		obj.value = obj.value.replace(RegExp , '');
		return RegExp.test(obj.value);
	}
}
/**
 *   한글,영문,숫자,띄워쓰기 만 입력 가능하도록 체크
 */
commonUtil.specialCharactersSpace = function(obj){
   var RegExp = /[^a-z|A-Z|0-9|ㄱ-ㅎ|가-힣|\s]/g;
   if (RegExp.test(obj.value)) {
      commonUtil.alert("특수문자는 입력할 수 없습니다.");
      obj.value = obj.value.replace(RegExp , '');
      return RegExp.test(obj.value);
   }
}
/**
 * 숫자체크
 */
commonUtil.is_number = function(obj){
   var RegExp = /[^0-9]/g;
   if (RegExp.test(obj.value)) {
      commonUtil.alert("숫자만 입력 가능합니다.");
      obj.value = obj.value.replace(RegExp , '');
      return RegExp.test(obj.value);
   }
}

/**
 * 숫자체크 (-/+ 부호까지 체크)
 */

commonUtil.is_number_exp = function(obj){
   var RegExp = /[^0-9+-]/g;
   if (RegExp.test(obj.value)) {
      commonUtil.alert("숫자와 '-', '+'만 입력 가능합니다.");
      obj.value = obj.value.replace(RegExp , '');
      return RegExp.test(obj.value);
   }
}

/**
 * 소수점 자리수에 맞는 숫자인지 체크 (소수점 2자리까지 체크)
 */
commonUtil.is_float_2 = function(v){
   var reg = /^[-+]?\d+.?\d?\d?$/;
    return reg.test(v);
}

/*
 * 비밀번호 형식 체크 (영문+숫자+특수문자 포함 여부 && 8~16자리 자리수 체크,)
 */
commonUtil.engNumberSpecialCharacters = function(v){
   var passwordCheckLowerEnglish = v.search(/[a-z]/g);
   var passwordCheckUpperEnglish = v.search(/[A-Z]/g);
   var passwordCheckSpecialCharacters = v.search(/[\!\"\#\$\%\&\'\(\)\*\+\,\-\.\/\:\;\<\=\>\?\@\[\＼\]\^\_\`\{\|\}\~\\]/g);
   var passwordCheckNumber = v.search(/[0-9]/g);

   // 비밀번호 규칙이 3가지 이상 포함되는지 체크
   var passwordRuleCnt = 0;

   // 영문 소문자 비밀번호 포함 여부 체크
   if(passwordCheckLowerEnglish > -1){
       passwordRuleCnt++;
   }
   // 영문 대문자 비밀번호 포함 여부 체크
   if(passwordCheckUpperEnglish > -1){
       passwordRuleCnt++;
   }
   // 특수문자 비밀번호 포함 여부 체크
   if(passwordCheckSpecialCharacters > -1){
       passwordRuleCnt++;
   }
   // 숫자 비밀번호 포함 여부 체크
   if(passwordCheckNumber > -1){
       passwordRuleCnt++;
   }

   return (passwordRuleCnt >= 3 ? true : false);
}
/*
 * 이메일 형식 체크
 */
commonUtil.emailCheck = function(v){
   var reg = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
   return reg.test(v);
}

/*
 * 전화번호, 휴대폰번호 정합성 체크
 */
commonUtil.is_phoneNo = function(v){
   //var reg = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
   var reg = /^(01[0|1|6|7|8|9]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/;
    return reg.test(v);
}

/*
 * 프로젝트 권한명 가져오기
 */
commonUtil.getProjectAuthCdNm = function(projectAuthCd, collectionYn, refineYn, labelingYn){
   if(projectAuthCd == "E"){
      return "총괄관리자";
   } else if(projectAuthCd == "P"){
      return "PMO";
   } else if(projectAuthCd == "G"){
      return "그룹관리자";
   } else if(projectAuthCd == "W"){
      if(collectionYn == "Y" && refineYn != "Y" && labelingYn != "Y"){
         return "워커(수집)";
      } else if(collectionYn == "Y" && refineYn == "Y" && labelingYn != "Y"){
         return "워커(수집/정제)";
      } else if(collectionYn == "Y" && refineYn != "Y" && labelingYn == "Y"){
         return "워커(수집/라벨링)";
      } else if(collectionYn == "Y" && refineYn == "Y" && labelingYn == "Y"){
         return "워커(수집/정제/라벨링)";
      } else if(collectionYn != "Y" && refineYn == "Y" && labelingYn != "Y"){
         return "워커(정제)";
      } else if(collectionYn != "Y" && refineYn == "Y" && labelingYn == "Y"){
         return "워커(정제/라벨링)";
      } else if(collectionYn != "Y" && refineYn != "Y" && labelingYn == "Y"){
         return "워커(라벨링)";
      }
   } else {
      return "-";
   }
}

/**
 * 전화번호 형식으로 바꾸기
 */
commonUtil.phone_format = function(num){
   return num.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
}

/**
 * 좌측문자열채우기
 * @params
 *  - str : 원 문자열
 *  - padLen : 최대 채우고자 하는 길이
 *  - padStr : 채우고자하는 문자(char)
 */
commonUtil.lpad = function(str, padLen, padStr) {
    if (padStr.length > padLen) {
        return str;
    }
    str += ""; // 문자로
    padStr += ""; // 문자로
    while (str.length < padLen)
        str = padStr + str;
    str = str.length >= padLen ? str.substring(0, padLen) : str;
    return str;
}

/*
 * 파일용량 텍스트 취득 ( 파일용량 )
 */
commonUtil.getFileSizeText = function( val ) {
	var returnTxt = "0MB";

	if (! isNaN( val )) {
		// 파일 용량 텍스트 세팅
		if (val < (1024*1024)) {
			returnTxt = (val/1024).toFixed(2) + "KB";
		} else {
			returnTxt = (val/1024/1024).toFixed(2) + "MB";
		}
	}

	return returnTxt;
}

/*
 * 에디터로 입력된 상세내용 스타일 적용. 반영테스트중.
 */	
commonUtil.setIframeNoEdit = function( idVal ) {
	$("#"+idVal).contents().find("body").prop("style", "all: initial;");
	$("#"+idVal).contents().find("body").children().css({  
		// 페이지 기본css 하드코딩입니다. 에디터에서 기본css 적용시키면, li태그를 못씁니다.
		  "margin"      : "0.5em 0em 0em"
		, "white-space" : "pre-wrap"
		, "word-break"  : "break-all"
		, "font-family" : "'Roboto', 'Noto Sans KR', sans-serif"
		, "color"       : "#666"
		, "font-size"   : "14px"
	});
	$("#"+idVal).contents().find("body").find("h1,h2,h3,h4").css("font-size", "");  // h* 태그는 폰트크기 속성 삭제
	$("#"+idVal).contents().find("body").find("ol, ul, li").prop("style", "");      // 해당태그의 스타일 제거
	$("#"+idVal).contents().find("body").find("li").css({                           // li태그 줄간격 및 기타속성 설정
		  "margin"      : "0.2rem 0rem 0rem"
		, "white-space" : "pre-wrap"
		, "word-break"  : "break-all"
		, "font-family" : "'Roboto', 'Noto Sans KR', sans-serif"
		, "color"       : "#666"
		, "font-size"   : "14px"
	});
}

/*
 * 줄바꿈 치환
 */
commonUtil.setBr = function( val ) {
	var returnTxt = "";

	if (val !== "") {
		returnTxt = val.replace(/\r\n|\n\r|\r|\n/gm, "<br />");
	}

	return returnTxt;
}

/*
 * 파일 확장자에 대한 이미지 class값 취득 ( 파일확장자 )
 */
commonUtil.getFileExtClass = function( val ) {
	var returnTxt = "";

	if (val !== undefined && val !== null && val.trim() !== "") {
		val = val.toLowerCase();

		if      (val === "xls" || val === "xlsx") { returnTxt = "data_xls"; }
		else if (val === "doc" || val === "docx") { returnTxt = "data_doc"; }
		else if (val === "ppt" || val === "pptx") { returnTxt = "data_ppt"; }
		else if (val === "hwp" || val === "hwpx") { returnTxt = "data_hwp"; }
		else if ($.inArray( val, ["bmp","jpg","jpeg","gif","png","tif","tiff"]) > -1) { returnTxt = "data_img"; }
		else if ($.inArray( val, ["alz","zip","sod","wav","mp4","pdf","txt"]  ) > -1) { returnTxt = "data_" + val; }
		else returnTxt = "data_etc";
	}

	return returnTxt;
}
// ////////////////////////////////////////////////////////////////////////////
// Main
// ////////////////////////////////////////////////////////////////////////////
$(window).on('load', function() {
});