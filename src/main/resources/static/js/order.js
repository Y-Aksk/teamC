// メインメニュー用の開閉処理
$(function() {
	$('#menubar_hdr').click(function() {
		$('#menubar').slideToggle();
		$(this).toggleClass('close');
	});
});

// 同一ページへのリンクの場合にメニューを閉じる処理
$(function() {
	$('#menubar a[href^="#"]').click(function(){
		$('#menubar').hide();
		$('#menubar_hdr').removeClass('close');
	});
});

// 汎用開閉処理
$(function() {
	$('.openclose').next().hide();
	$('.openclose').click(function() {
		$(this).next().slideToggle();
		$('.openclose').not(this).next().slideUp();
	});
});

//pagetopのボタンを出したり消したりするスクリプト
$(function(){
    var scroll = $('.pagetop');
    var scrollShow = $('.pagetop-show');
        $(scroll).hide();
        $(window).scroll(function(){
            if($(this).scrollTop() >= 300) {
                $(scroll).fadeIn(500).addClass(scrollShow);
            } else {
                $(scroll).fadeOut(500).removeClass(scrollShow);
            }
        });
});

//スムーススクロールのスクリプト
$(function(){
    $('a[href^="#"]').click(function(){
        var href = $(this).attr('href');
        var target = href == '#' ? 0 : $(href).offset().top;
            $('body,html').animate({scrollTop:target},500);
            return false;
    });
});

// ここから下はorder.html用
$(function() {
    // チェックボックスのクリックを無効化します。
    $('.image_box .disabled_checkbox').click(function() {
      return false;
    });
  
    // 画像がクリックされた時の処理です。
    $('img.thumbnail').on('click', function() {
      if (!$(this).is('.checked')) {
        // チェックが入っていない画像をクリックした場合、チェックを入れます。
        $(this).addClass('checked');
      } else {
        // チェックが入っている画像をクリックした場合、チェックを外します。
        $(this).removeClass('checked')
      }
    });
  });

// いずれかのチェックボックスにチェックすると、ボタンが有効になる。
$(function(){
  // 初期状態のボタンは無効
  $("#btn").prop("disabled", true);

  $("input[type='checkbox']").on('click', function () {
        //チェックされているチェックボックスの数
        if ($(".disabled_checkbox:checked").length > 0) {
          //ボタン有効
          $("#btn").prop("disabled", false);
        } else {
          //ボタン無効
          $("#btn").prop("disabled", true);
        }
  });
});

