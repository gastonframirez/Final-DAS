(function($) {
  'use strict';
  $.fn.andSelf = function() {
    return this.addBack.apply(this, arguments);
  }


  if ($('.full-width').length) {
    $('.full-width').owlCarousel({
      loop: false,
      margin: 10,
      items: 3,
      nav: true,
      autoplay: false,
      autoplayTimeout: 5500,
      navText: ["<i class='mdi mdi-chevron-left'></i>", "<i class='mdi mdi-chevron-right'></i>"]
    });
  }


})(jQuery);