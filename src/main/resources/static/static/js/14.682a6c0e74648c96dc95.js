webpackJsonp([14],{505:function(t,e,i){i(739);var s=i(58)(i(676),i(768),null,null);t.exports=s.exports},565:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=i(566),o=i.n(s);e.default={data:function(){return{showFlag:!0,iconShowFlag:!0,listShowFlag:!1,userList:[],selectItem:0}},props:{userType:{Number:Number,default:0},chooseType:{String:String,default:""}},created:function(){this.userList=JSON.parse(localStorage.getItem("USERLIST")),this.$emit("userData",this.userList.list[0])},computed:{currentRouter:function(){return this.$store.state.route.path}},watch:{currentRouter:function(t){switch(t){case"/parent/parentIndex":case"/principal/principalIndex":case"/teacher/teacherIndex":this.showFlag=!0;break;default:this.showFlag=!1}}},methods:{showUserList:function(){var t=this;this.setShowFlag(),this.$nextTick(function(){t.scroll=new o.a(t.$refs.userListWrapper,{click:!0})})},chooseUser:function(t,e){this.$emit("userData",t),this.selectItem=e,this.setShowFlag()},setShowFlag:function(){var t=this;this.iconShowFlag?this.iconShowFlag=!this.iconShowFlag:setTimeout(function(){t.iconShowFlag=!t.iconShowFlag},200),this.listShowFlag=!this.listShowFlag}}}},566:function(t,e,i){/*!
 * better-scroll v0.4.0
 * (c) 2016-2017 ustbhuangyi
 * Released under the MIT License.
 */
!function(e,i){t.exports=i()}(0,function(){"use strict";function t(t){return!1!==d&&("standard"===d?t:d+t.charAt(0).toUpperCase()+t.substr(1))}function e(t,e,i,s){t.addEventListener(e,i,{passive:!1,capture:!!s})}function i(t,e,i,s){t.removeEventListener(e,i,!!s)}function s(t){for(var e=0,i=0;t;)e-=t.offsetLeft,i-=t.offsetTop,t=t.offsetParent;return{left:e,top:i}}function o(t){if(t instanceof window.SVGElement){var e=t.getBoundingClientRect();return{top:e.top,left:e.left,width:e.width,height:e.height}}return{top:t.offsetTop,left:t.offsetLeft,width:t.offsetWidth,height:t.offsetHeight}}function n(t,e){for(var i in e)if(e[i].test(t[i]))return!0;return!1}function r(t,e){var i=document.createEvent("Event");i.initEvent(e,!0,!0),i.pageX=t.pageX,i.pageY=t.pageY,t.target.dispatchEvent(i)}function a(t){var e=t.target;if(!/(SELECT|INPUT|TEXTAREA)/i.test(e.tagName)){var i=document.createEvent(window.MouseEvent?"MouseEvents":"Event");i.initEvent("click",!0,!0),i._constructed=!0,e.dispatchEvent(i)}}function h(t,e){e.firstChild?l(t,e.firstChild):e.appendChild(t)}function l(t,e){e.parentNode.insertBefore(t,e)}function c(t,e){for(var i in e)t[i]=e[i]}function p(t,e,i,s,o,n){var r=t-e,a=Math.abs(r)/i,h=n.deceleration,l=n.itemHeight,c=n.swipeBounceTime,p=n.bounceTime,u=n.swipeTime,d=n.wheel?4:15,f=t+a/h*(r<0?-1:1);return n.wheel&&l&&(f=Math.round(f/l)*l),f<s?(f=o?s-o/d*a:s,u=c-p):f>0&&(f=o?o/d*a:0,u=c-p),{destination:Math.round(f),duration:u}}var u=document.createElement("div").style,d=function(){var t={webkit:"webkitTransform",Moz:"MozTransform",O:"OTransform",ms:"msTransform",standard:"transform"};for(var e in t)if(void 0!==u[t[e]])return e;return!1}(),f=t("transform"),m=t("perspective")in u,g="ontouchstart"in window,A=!1!==f,v=t("transition")in u,w={transform:f,transitionTimingFunction:t("transitionTimingFunction"),transitionDuration:t("transitionDuration"),transitionDelay:t("transitionDelay"),transformOrigin:t("transformOrigin"),transitionEnd:t("transitionEnd")},y={touchstart:1,touchmove:1,touchend:1,mousedown:2,mousemove:2,mouseup:2},b=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||window.oRequestAnimationFrame||function(t){return window.setTimeout(t,(t.interval||100/60)/2)}}(),C=function(){return window.cancelAnimationFrame||window.webkitCancelAnimationFrame||window.mozCancelAnimationFrame||window.oCancelAnimationFrame||function(t){window.clearTimeout(t)}}(),x=/Android /.test(window.navigator.appVersion)&&!/Chrome\/\d/.test(window.navigator.appVersion),T={swipe:{style:"cubic-bezier(0.23, 1, 0.32, 1)",fn:function(t){return 1+--t*t*t*t*t}},swipeBounce:{style:"cubic-bezier(0.25, 0.46, 0.45, 0.94)",fn:function(t){return t*(2-t)}},bounce:{style:"cubic-bezier(0.165, 0.84, 0.44, 1)",fn:function(t){return 1- --t*t*t*t}}},B=function(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")},S=function(){function t(t,e){for(var i=0;i<e.length;i++){var s=e[i];s.enumerable=s.enumerable||!1,s.configurable=!0,"value"in s&&(s.writable=!0),Object.defineProperty(t,s.key,s)}}return function(e,i,s){return i&&t(e.prototype,i),s&&t(e,s),e}}(),k=function(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)},L=function(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e},E=function(){function t(t,e){var i=[],s=!0,o=!1,n=void 0;try{for(var r,a=t[Symbol.iterator]();!(s=(r=a.next()).done)&&(i.push(r.value),!e||i.length!==e);s=!0);}catch(t){o=!0,n=t}finally{try{!s&&a.return&&a.return()}finally{if(o)throw n}}return i}return function(e,i){if(Array.isArray(e))return e;if(Symbol.iterator in Object(e))return t(e,i);throw new TypeError("Invalid attempt to destructure non-iterable instance")}}(),_=function(t){if(Array.isArray(t)){for(var e=0,i=Array(t.length);e<t.length;e++)i[e]=t[e];return i}return Array.from(t)},Y=function(){function t(){B(this,t),this._events={}}return S(t,[{key:"on",value:function(t,e){var i=arguments.length>2&&void 0!==arguments[2]?arguments[2]:this;this._events[t]||(this._events[t]=[]),this._events[t].push([e,i])}},{key:"once",value:function(t,e){function i(){this.off(t,i),o||(o=!0,e.apply(s,arguments))}var s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:this,o=!1;this.on(t,i)}},{key:"off",value:function(t,e){var i=this._events[t];if(i)for(var s=i.length;s--;)i[s][0]===e&&(i[s][0]=void 0)}},{key:"trigger",value:function(t){var e=this._events[t];if(e)for(var i=e.length,s=[].concat(_(e)),o=0;o<i;o++){var n=s[o],r=E(n,2),a=r[0],h=r[1];a&&a.apply(h,[].slice.call(arguments,1))}}}]),t}(),X=function(t){function l(t,e){B(this,l);var i=L(this,(l.__proto__||Object.getPrototypeOf(l)).call(this));return i.wrapper="string"==typeof t?document.querySelector(t):t,i.scroller=i.wrapper.children[0],i.scrollerStyle=i.scroller.style,i.options={startX:0,startY:0,scrollY:!0,directionLockThreshold:5,momentum:!0,bounce:!0,selectedIndex:0,rotate:25,wheel:!1,snap:!1,snapLoop:!1,snapThreshold:.1,swipeTime:2500,bounceTime:700,adjustTime:400,swipeBounceTime:1200,deceleration:.001,momentumLimitTime:300,momentumLimitDistance:15,resizePolling:60,preventDefault:!0,preventDefaultException:{tagName:/^(INPUT|TEXTAREA|BUTTON|SELECT)$/},HWCompositing:!0,useTransition:!0,useTransform:!0},c(i.options,e),i.translateZ=i.options.HWCompositing&&m?" translateZ(0)":"",i.options.useTransition=i.options.useTransition&&v,i.options.useTransform=i.options.useTransform&&A,i.options.eventPassthrough=!0===i.options.eventPassthrough?"vertical":i.options.eventPassthrough,i.options.preventDefault=!i.options.eventPassthrough&&i.options.preventDefault,i.options.scrollX="horizontal"!==i.options.eventPassthrough&&i.options.scrollX,i.options.scrollY="vertical"!==i.options.eventPassthrough&&i.options.scrollY,i.options.freeScroll=i.options.freeScroll&&!i.options.eventPassthrough,i.options.directionLockThreshold=i.options.eventPassthrough?0:i.options.directionLockThreshold,!0===i.options.tap&&(i.options.tap="tap"),i._init(),i.options.snap&&i._initSnap(),i.refresh(),i.options.snap||i.scrollTo(i.options.startX,i.options.startY),i.enable(),i}return k(l,t),S(l,[{key:"_init",value:function(){this.x=0,this.y=0,this.directionX=0,this.directionY=0,this._addEvents()}},{key:"_initSnap",value:function(){var t=this;if(this.currentPage={},this.options.snapLoop){var e=this.scroller.children;e.length>0&&(h(e[e.length-1].cloneNode(!0),this.scroller),this.scroller.appendChild(e[1].cloneNode(!0)))}"string"==typeof this.options.snap&&(this.options.snap=this.scroller.querySelectorAll(this.options.snap)),this.on("refresh",function(){if(t.pages=[],t.wrapperWidth&&t.wrapperHeight&&t.scrollerWidth&&t.scrollerHeight){var e=t.options.snapStepX||t.wrapperWidth,i=t.options.snapStepY||t.wrapperHeight,s=0,n=void 0,r=void 0,a=void 0,h=0,l=void 0,c=0,p=void 0,u=void 0,d=void 0;if(!0===t.options.snap)for(r=Math.round(e/2),a=Math.round(i/2);s>-t.scrollerWidth;){for(t.pages[h]=[],l=0,n=0;n>-t.scrollerHeight;)t.pages[h][l]={x:Math.max(s,t.maxScrollX),y:Math.max(n,t.maxScrollY),width:e,height:i,cx:s-r,cy:n-a},n-=i,l++;s-=e,h++}else for(u=t.options.snap,l=u.length,p=-1;h<l;h++)d=o(u[h]),(0===h||d.left<=o(u[h-1]).left)&&(c=0,p++),t.pages[c]||(t.pages[c]=[]),s=Math.max(-d.left,t.maxScrollX),n=Math.max(-d.top,t.maxScrollY),r=s-Math.round(d.width/2),a=n-Math.round(d.height/2),t.pages[c][p]={x:s,y:n,width:d.width,height:d.height,cx:r,cy:a},s>t.maxScrollX&&c++;var f=t.options.snapLoop?1:0;t.goToPage(t.currentPage.pageX||f,t.currentPage.pageY||0,0),t.options.snapThreshold%1==0?(t.snapThresholdX=t.options.snapThreshold,t.snapThresholdY=t.options.snapThreshold):(t.snapThresholdX=Math.round(t.pages[t.currentPage.pageX][t.currentPage.pageY].width*t.options.snapThreshold),t.snapThresholdY=Math.round(t.pages[t.currentPage.pageX][t.currentPage.pageY].height*t.options.snapThreshold))}}),this.on("scrollEnd",function(){t.options.snapLoop&&(0===t.currentPage.pageX&&t.goToPage(t.pages.length-2,t.currentPage.pageY,0),t.currentPage.pageX===t.pages.length-1&&t.goToPage(1,t.currentPage.pageY,0))}),this.on("flick",function(){var e=t.options.snapSpeed||Math.max(Math.max(Math.min(Math.abs(t.x-t.startX),1e3),Math.min(Math.abs(t.y-t.startY),1e3)),300);t.goToPage(t.currentPage.pageX+t.directionX,t.currentPage.pageY+t.directionY,e)})}},{key:"_nearestSnap",value:function(t,e){if(!this.pages.length)return{x:0,y:0,pageX:0,pageY:0};var i=0;if(Math.abs(t-this.absStartX)<=this.snapThresholdX&&Math.abs(e-this.absStartY)<=this.snapThresholdY)return this.currentPage;t>0?t=0:t<this.maxScrollX&&(t=this.maxScrollX),e>0?e=0:e<this.maxScrollY&&(e=this.maxScrollY);for(var s=this.pages.length;i<s;i++)if(t>=this.pages[i][0].cx){t=this.pages[i][0].x;break}s=this.pages[i].length;for(var o=0;o<s;o++)if(e>=this.pages[0][o].cy){e=this.pages[0][o].y;break}return i===this.currentPage.pageX&&(i+=this.directionX,i<0?i=0:i>=this.pages.length&&(i=this.pages.length-1),t=this.pages[i][0].x),o===this.currentPage.pageY&&(o+=this.directionY,o<0?o=0:o>=this.pages[0].length&&(o=this.pages[0].length-1),e=this.pages[0][o].y),{x:t,y:e,pageX:i,pageY:o}}},{key:"_addEvents",value:function(){var t=e;this._handleEvents(t)}},{key:"_removeEvents",value:function(){var t=i;this._handleEvents(t)}},{key:"_handleEvents",value:function(t){var e=this.options.bindToWrapper?this.wrapper:window;t(window,"orientationchange",this),t(window,"resize",this),this.options.click&&t(this.wrapper,"click",this),this.options.disableMouse||(t(this.wrapper,"mousedown",this),t(e,"mousemove",this),t(e,"mousecancel",this),t(e,"mouseup",this)),g&&!this.options.disableTouch&&(t(this.wrapper,"touchstart",this),t(e,"touchmove",this),t(e,"touchcancel",this),t(e,"touchend",this)),t(this.scroller,w.transitionEnd,this)}},{key:"_start",value:function(t){var e=y[t.type];if((1===e||0===t.button)&&!(!this.enabled||this.destroyed||this.initiated&&this.initiated!==e)){if(this.initiated=e,!this.options.preventDefault||x||n(t.target,this.options.preventDefaultException)||t.preventDefault(),this.moved=!1,this.distX=0,this.distY=0,this.directionX=0,this.directionY=0,this.directionLocked=0,this._transitionTime(),this.startTime=+new Date,this.options.wheel&&(this.target=t.target),this.options.useTransition&&this.isInTransition){this.isInTransition=!1;var i=this.getComputedPosition();this._translate(i.x,i.y),this.options.wheel?this.target=this.items[Math.round(-i.y/this.itemHeight)]:this.trigger("scrollEnd",{x:this.x,y:this.y})}var s=t.touches?t.touches[0]:t;this.startX=this.x,this.startY=this.y,this.absStartX=this.x,this.absStartY=this.y,this.pointX=s.pageX,this.pointY=s.pageY,this.trigger("beforeScrollStart")}}},{key:"_move",value:function(t){if(this.enabled&&!this.destroyed&&y[t.type]===this.initiated){this.options.preventDefault&&t.preventDefault();var e=t.touches?t.touches[0]:t,i=e.pageX-this.pointX,s=e.pageY-this.pointY;this.pointX=e.pageX,this.pointY=e.pageY,this.distX+=i,this.distY+=s;var o=Math.abs(this.distX),n=Math.abs(this.distY),r=+new Date;if(!(r-this.endTime>this.options.momentumLimitTime&&n<this.options.momentumLimitDistance&&o<this.options.momentumLimitDistance)){if(this.directionLocked||this.options.freeScroll||(o>n+this.options.directionLockThreshold?this.directionLocked="h":n>=o+this.options.directionLockThreshold?this.directionLocked="v":this.directionLocked="n"),"h"===this.directionLocked){if("vertical"===this.options.eventPassthrough)t.preventDefault();else if("horizontal"===this.options.eventPassthrough)return void(this.initiated=!1);s=0}else if("v"===this.directionLocked){if("horizontal"===this.options.eventPassthrough)t.preventDefault();else if("vertical"===this.options.eventPassthrough)return void(this.initiated=!1);i=0}i=this.hasHorizontalScroll?i:0,s=this.hasVerticalScroll?s:0;var a=this.x+i,h=this.y+s;(a>0||a<this.maxScrollX)&&(a=this.options.bounce?this.x+i/3:a>0?0:this.maxScrollX),(h>0||h<this.maxScrollY)&&(h=this.options.bounce?this.y+s/3:h>0?0:this.maxScrollY),this.moved||(this.moved=!0,this.trigger("scrollStart")),this._translate(a,h),r-this.startTime>this.options.momentumLimitTime&&(this.startTime=r,this.startX=this.x,this.startY=this.y,1===this.options.probeType&&this.trigger("scroll",{x:this.x,y:this.y})),this.options.probeType>1&&this.trigger("scroll",{x:this.x,y:this.y});var l=document.documentElement.scrollLeft||window.pageXOffset||document.body.scrollLeft,c=document.documentElement.scrollTop||window.pageYOffset||document.body.scrollTop,p=this.pointX-l,u=this.pointY-c;(p>document.documentElement.clientWidth-this.options.momentumLimitDistance||p<this.options.momentumLimitDistance||u<this.options.momentumLimitDistance||u>document.documentElement.clientHeight-this.options.momentumLimitDistance)&&this._end(t)}}}},{key:"_end",value:function(t){if(this.enabled&&!this.destroyed&&y[t.type]===this.initiated&&(this.initiated=!1,this.options.preventDefault&&!n(t.target,this.options.preventDefaultException)&&t.preventDefault(),this.trigger("touchend",{x:this.x,y:this.y}),!this.resetPosition(this.options.bounceTime,T.bounce))){this.isInTransition=!1;var e=Math.round(this.x),i=Math.round(this.y);if(!this.moved){if(this.options.wheel){if(this.target&&"wheel-scroll"===this.target.className){var o=Math.abs(Math.round(i/this.itemHeight)),h=Math.round((this.pointY+s(this.target).top-this.itemHeight/2)/this.itemHeight);this.target=this.items[o+h]}this.scrollToElement(this.target,this.options.adjustTime,!0,!0,T.swipe)}else this.options.tap&&r(t,this.options.tap),this.options.click&&a(t);return void this.trigger("scrollCancel")}this.scrollTo(e,i);var l=e-this.absStartX,c=i-this.absStartY;this.directionX=l>0?-1:l<0?1:0,this.directionY=c>0?-1:c<0?1:0,this.endTime=+new Date;var u=this.endTime-this.startTime,d=Math.abs(e-this.startX),f=Math.abs(i-this.startY);if(this._events.flick&&u<this.options.momentumLimitTime&&d<this.options.momentumLimitDistance&&f<this.options.momentumLimitDistance)return void this.trigger("flick");var m=0;if(this.options.momentum&&u<this.options.momentumLimitTime&&(f>this.options.momentumLimitDistance||d>this.options.momentumLimitDistance)){var g=this.hasHorizontalScroll?p(this.x,this.startX,u,this.maxScrollX,this.options.bounce?this.wrapperWidth:0,this.options):{destination:e,duration:0},A=this.hasVerticalScroll?p(this.y,this.startY,u,this.maxScrollY,this.options.bounce?this.wrapperHeight:0,this.options):{destination:i,duration:0};e=g.destination,i=A.destination,m=Math.max(g.duration,A.duration),this.isInTransition=1}else this.options.wheel&&(i=Math.round(i/this.itemHeight)*this.itemHeight,m=this.options.adjustTime);var v=T.swipe;if(this.options.snap){var w=this._nearestSnap(e,i);this.currentPage=w,m=this.options.snapSpeed||Math.max(Math.max(Math.min(Math.abs(e-w.x),1e3),Math.min(Math.abs(i-w.y),1e3)),300),e=w.x,i=w.y,this.directionX=0,this.directionY=0,v=T.bounce}if(e!==this.x||i!==this.y)return(e>0||e<this.maxScrollX||i>0||i<this.maxScrollY)&&(v=T.swipeBounce),void this.scrollTo(e,i,m,v);this.options.wheel&&(this.selectedIndex=0|Math.abs(this.y/this.itemHeight)),this.trigger("scrollEnd",{x:this.x,y:this.y})}}},{key:"_resize",value:function(){var t=this;this.enabled&&(clearTimeout(this.resizeTimeout),this.resizeTimeout=setTimeout(function(){t.refresh()},this.options.resizePolling))}},{key:"_startProbe",value:function(){function t(){var i=e.getComputedPosition();e.trigger("scroll",i),e.isInTransition&&(e.probeTimer=b(t))}C(this.probeTimer),this.probeTimer=b(t);var e=this}},{key:"_transitionTime",value:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:0;if(this.scrollerStyle[w.transitionDuration]=e+"ms",this.options.wheel&&!x)for(var i=0;i<this.items.length;i++)this.items[i].style[w.transitionDuration]=e+"ms";!e&&x&&(this.scrollerStyle[w.transitionDuration]="0.001s",b(function(){"0.0001ms"===t.scrollerStyle[w.transitionDuration]&&(t.scrollerStyle[w.transitionDuration]="0s")}))}},{key:"_transitionTimingFunction",value:function(t){if(this.scrollerStyle[w.transitionTimingFunction]=t,this.options.wheel&&!x)for(var e=0;e<this.items.length;e++)this.items[e].style[w.transitionTimingFunction]=t}},{key:"_transitionEnd",value:function(t){t.target===this.scroller&&this.isInTransition&&(this._transitionTime(),this.resetPosition(this.options.bounceTime,T.bounce)||(this.isInTransition=!1,this.trigger("scrollEnd",{x:this.x,y:this.y})))}},{key:"_translate",value:function(t,e){if(this.options.useTransform?this.scrollerStyle[w.transform]="translate("+t+"px,"+e+"px)"+this.translateZ:(t=Math.round(t),e=Math.round(e),this.scrollerStyle.left=t+"px",this.scrollerStyle.top=e+"px"),this.options.wheel&&!x)for(var i=0;i<this.items.length;i++){var s=this.options.rotate*(e/this.itemHeight+i);this.items[i].style[w.transform]="rotateX("+s+"deg)"}this.x=t,this.y=e}},{key:"enable",value:function(){this.enabled=!0}},{key:"disable",value:function(){this.enabled=!1}},{key:"refresh",value:function(){this.wrapper.offsetHeight;this.wrapperWidth=parseInt(this.wrapper.style.width)||this.wrapper.clientWidth,this.wrapperHeight=parseInt(this.wrapper.style.height)||this.wrapper.clientHeight,this.scrollerWidth=parseInt(this.scroller.style.width)||this.scroller.clientWidth,this.scrollerHeight=parseInt(this.scroller.style.height)||this.scroller.clientHeight,this.options.wheel?(this.items=this.scroller.children,this.options.itemHeight=this.itemHeight=this.items.length?this.items[0].clientHeight:0,void 0===this.selectedIndex&&(this.selectedIndex=this.options.selectedIndex),this.options.startY=-this.selectedIndex*this.itemHeight,this.maxScrollX=0,this.maxScrollY=-this.itemHeight*(this.items.length-1)):(this.maxScrollX=this.wrapperWidth-this.scrollerWidth,this.maxScrollY=this.wrapperHeight-this.scrollerHeight),this.hasHorizontalScroll=this.options.scrollX&&this.maxScrollX<0,this.hasVerticalScroll=this.options.scrollY&&this.maxScrollY<0,this.hasHorizontalScroll||(this.maxScrollX=0,this.scrollerWidth=this.wrapperWidth),this.hasVerticalScroll||(this.maxScrollY=0,this.scrollerHeight=this.wrapperHeight),this.endTime=0,this.directionX=0,this.directionY=0,this.wrapperOffset=s(this.wrapper),this.trigger("refresh"),this.resetPosition()}},{key:"resetPosition",value:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:0,e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:T.bounce,i=this.x;!this.hasHorizontalScroll||i>0?i=0:i<this.maxScrollX&&(i=this.maxScrollX);var s=this.y;return!this.hasVerticalScroll||s>0?s=0:s<this.maxScrollY&&(s=this.maxScrollY),(i!==this.x||s!==this.y)&&(this.scrollTo(i,s,t,e),!0)}},{key:"wheelTo",value:function(t){this.options.wheel&&(this.y=-t*this.itemHeight,this.scrollTo(0,this.y))}},{key:"scrollBy",value:function(t,e){var i=arguments.length>2&&void 0!==arguments[2]?arguments[2]:0,s=arguments.length>3&&void 0!==arguments[3]?arguments[3]:T.bounce;t=this.x+t,e=this.y+e,this.scrollTo(t,e,i,s)}},{key:"scrollTo",value:function(t,e,i){var s=arguments.length>3&&void 0!==arguments[3]?arguments[3]:T.bounce;this.isInTransition=this.options.useTransition&&i>0&&(t!==this.x||e!==this.y),i&&!this.options.useTransition||(this._transitionTimingFunction(s.style),this._transitionTime(i),this._translate(t,e),i&&3===this.options.probeType&&this._startProbe(),this.options.wheel&&(e>0?this.selectedIndex=0:e<this.maxScrollY?this.selectedIndex=this.items.length-1:this.selectedIndex=0|Math.abs(e/this.itemHeight)))}},{key:"getSelectedIndex",value:function(){return this.options.wheel&&this.selectedIndex}},{key:"getCurrentPage",value:function(){return this.options.snap&&this.currentPage}},{key:"scrollToElement",value:function(t,e,i,o,n){if(t&&(t=t.nodeType?t:this.scroller.querySelector(t),!this.options.wheel||"wheel-item"===t.className)){var r=s(t);r.left-=this.wrapperOffset.left,r.top-=this.wrapperOffset.top,!0===i&&(i=Math.round(t.offsetWidth/2-this.wrapper.offsetWidth/2)),!0===o&&(o=Math.round(t.offsetHeight/2-this.wrapper.offsetHeight/2)),r.left-=i||0,r.top-=o||0,r.left=r.left>0?0:r.left<this.maxScrollX?this.maxScrollX:r.left,r.top=r.top>0?0:r.top<this.maxScrollY?this.maxScrollY:r.top,this.options.wheel&&(r.top=Math.round(r.top/this.itemHeight)*this.itemHeight),e=void 0===e||null===e||"auto"===e?Math.max(Math.abs(this.x-r.left),Math.abs(this.y-r.top)):e,this.scrollTo(r.left,r.top,e,n)}}},{key:"getComputedPosition",value:function(){var t=window.getComputedStyle(this.scroller,null),e=void 0,i=void 0;return this.options.useTransform?(t=t[w.transform].split(")")[0].split(", "),e=+(t[12]||t[4]),i=+(t[13]||t[5])):(e=+t.left.replace(/[^-\d.]/g,""),i=+t.top.replace(/[^-\d.]/g,"")),{x:e,y:i}}},{key:"goToPage",value:function(t,e,i){var s=arguments.length>3&&void 0!==arguments[3]?arguments[3]:T.bounce;t>=this.pages.length?t=this.pages.length-1:t<0&&(t=0),e>=this.pages[t].length?e=this.pages[t].length-1:e<0&&(e=0);var o=this.pages[t][e].x,n=this.pages[t][e].y;i=void 0===i?this.options.snapSpeed||Math.max(Math.max(Math.min(Math.abs(o-this.x),1e3),Math.min(Math.abs(n-this.y),1e3)),300):i,this.currentPage={x:o,y:n,pageX:t,pageY:e},this.scrollTo(o,n,i,s)}},{key:"next",value:function(t,e){var i=this.currentPage.pageX,s=this.currentPage.pageY;i++,i>=this.pages.length&&this.hasVerticalScroll&&(i=0,s++),this.goToPage(i,s,t,e)}},{key:"prev",value:function(t,e){var i=this.currentPage.pageX,s=this.currentPage.pageY;i--,i<0&&this.hasVerticalScroll&&(i=0,s--),this.goToPage(i,s,t,e)}},{key:"destroy",value:function(){this._removeEvents(),this.destroyed=!0,this.trigger("destroy")}},{key:"handleEvent",value:function(t){switch(t.type){case"touchstart":case"mousedown":this._start(t);break;case"touchmove":case"mousemove":this._move(t);break;case"touchend":case"mouseup":case"touchcancel":case"mousecancel":this._end(t);break;case"orientationchange":case"resize":this._resize();break;case"transitionend":case"webkitTransitionEnd":case"oTransitionEnd":case"MSTransitionEnd":this._transitionEnd(t);break;case"click":!this.enabled||t._constructed||/(SELECT|INPUT|TEXTAREA)/i.test(t.target.tagName)||(t.preventDefault(),t.stopPropagation())}}}]),l}(Y);return X.Version="0.4.0",X})},567:function(t,e,i){e=t.exports=i(499)(),e.push([t.i,'.userList{position:absolute;right:0;top:22rem;z-index:99}.userList .show-list-icon{font-size:0;border-top:1px solid #888;border-bottom:1px solid #888;border-left:1px solid #888;text-align:center;background:#face87}.userList .show-list-icon span{display:inline-block;padding:.5rem .8rem;line-height:1.5rem;font-size:1.2rem;color:#fff}.userList .move-enter-active,.userList .move-leave-active{-webkit-transition:all .2s linear;transition:all .2s linear;-webkit-transform:translateZ(0);transform:translateZ(0)}.userList .move-enter,.userList .move-leave{-webkit-transform:translate3d(100%,0,0);transform:translate3d(100%,0,0);opacity:0}.userList .userList-wrapper{padding:0 1.5rem;max-height:19.8rem;overflow:hidden;background:#face87;text-align:center;font-size:1.2rem;color:#fff;border-top:1px solid #888;border-bottom:1px solid #888;border-left:1px solid #888;border-top-left-radius:1.5rem;border-bottom-left-radius:1.5rem}.userList .userList-wrapper .userItem{height:4rem;line-height:4rem;position:relative}.userList .userList-wrapper .userItem:after{display:block;position:absolute;left:0;bottom:0;width:100%;border-top:1px solid #888;content:" "}.userList .userList-wrapper .userItem:last-child:after{display:none}.userList .userList-wrapper .nowUser{font-size:1.5rem;font-weight:600}',"",{version:3,sources:["/media/ren/办公/code/web/xiaonuo-vue/src/components/index/userList.vue"],names:[],mappings:"AACA,UACE,kBAAmB,AACnB,QAAS,AACT,UAAW,AACX,UAAY,CAEb,AACD,0BACE,YAAa,AACb,0BAA2B,AAC3B,6BAA8B,AAC9B,2BAA4B,AAC5B,kBAAmB,AACnB,kBAAoB,CACrB,AACD,+BACE,qBAAsB,AACtB,oBAAuB,AACvB,mBAAoB,AACpB,iBAAkB,AAClB,UAAY,CACb,AACD,0DAEE,kCAAoC,AACpC,0BAA4B,AAC5B,gCAAwC,AAChC,uBAAgC,CACzC,AACD,4CAEE,wCAA2C,AACnC,gCAAmC,AAC3C,SAAW,CACZ,AACD,4BACE,iBAAkB,AAClB,mBAAoB,AACpB,gBAAiB,AACjB,mBAAoB,AACpB,kBAAmB,AACnB,iBAAkB,AAClB,WAAY,AACZ,0BAA2B,AAC3B,6BAA8B,AAC9B,2BAA4B,AAC5B,8BAA+B,AAC/B,gCAAkC,CACnC,AACD,sCACE,YAAa,AACb,iBAAkB,AAClB,iBAAmB,CACpB,AACD,4CACE,cAAe,AACf,kBAAmB,AACnB,OAAQ,AACR,SAAU,AACV,WAAY,AACZ,0BAA2B,AAC3B,WAAa,CACd,AACD,uDACE,YAAc,CACf,AACD,qCACE,iBAAkB,AAClB,eAAiB,CAClB",file:"userList.vue",sourcesContent:["\n.userList {\n  position: absolute;\n  right: 0;\n  top: 22rem;\n  z-index: 99;\n/*定义元素最终移动到的位置，以及移动到此位置需要的时间*/\n}\n.userList .show-list-icon {\n  font-size: 0;\n  border-top: 1px solid #888;\n  border-bottom: 1px solid #888;\n  border-left: 1px solid #888;\n  text-align: center;\n  background: #face87;\n}\n.userList .show-list-icon span {\n  display: inline-block;\n  padding: 0.5rem 0.8rem;\n  line-height: 1.5rem;\n  font-size: 1.2rem;\n  color: #fff;\n}\n.userList .move-enter-active,\n.userList .move-leave-active {\n  -webkit-transition: all 0.2s linear;\n  transition: all 0.2s linear;\n  -webkit-transform: translate3d(0, 0, 0);\n          transform: translate3d(0, 0, 0);\n}\n.userList .move-enter,\n.userList .move-leave {\n  -webkit-transform: translate3d(100%, 0, 0);\n          transform: translate3d(100%, 0, 0);\n  opacity: 0;\n}\n.userList .userList-wrapper {\n  padding: 0 1.5rem;\n  max-height: 19.8rem;\n  overflow: hidden;\n  background: #face87;\n  text-align: center;\n  font-size: 1.2rem;\n  color: #fff;\n  border-top: 1px solid #888;\n  border-bottom: 1px solid #888;\n  border-left: 1px solid #888;\n  border-top-left-radius: 1.5rem;\n  border-bottom-left-radius: 1.5rem;\n}\n.userList .userList-wrapper .userItem {\n  height: 4rem;\n  line-height: 4rem;\n  position: relative;\n}\n.userList .userList-wrapper .userItem:after {\n  display: block;\n  position: absolute;\n  left: 0;\n  bottom: 0;\n  width: 100%;\n  border-top: 1px solid #888;\n  content: ' ';\n}\n.userList .userList-wrapper .userItem:last-child:after {\n  display: none;\n}\n.userList .userList-wrapper .nowUser {\n  font-size: 1.5rem;\n  font-weight: 600;\n}"],sourceRoot:""}])},568:function(t,e,i){var s=i(567);"string"==typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);i(500)("0f77d49b",s,!0)},569:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{directives:[{name:"show",rawName:"v-show",value:t.userList.list.length>1&&t.showFlag,expression:"userList.list.length > 1 && showFlag"}],staticClass:"userList",attrs:{currentRouter:t.currentRouter}},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.iconShowFlag,expression:"iconShowFlag"}],staticClass:"show-list-icon",on:{click:t.showUserList}},[i("span",[t._v("选择"),i("br"),t._v(t._s(t.chooseType))])]),t._v(" "),i("transition",{attrs:{name:"move"}},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.listShowFlag,expression:"listShowFlag"}],ref:"userListWrapper",staticClass:"userList-wrapper"},[i("ul",t._l(t.userList.list,function(e,s){return i("v-touch",{key:1,staticClass:"userItem border-1px",class:{nowUser:s===t.selectItem},attrs:{tag:"li"},on:{tap:function(i){t.chooseUser(e,s)}}},[30===t.userType?i("span",[t._v(t._s(e.studentName))]):31===t.userType?i("span",[t._v(t._s(e.schoolName))]):32===t.userType?i("span",[t._v(t._s(e.className))]):t._e()])}))])])],1)},staticRenderFns:[]}},571:function(t,e,i){i(568);var s=i(58)(i(565),i(569),null,null);t.exports=s.exports},676:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=i(201),o=i.n(s),n=i(571),r=i.n(n);e.default={data:function(){return{userId:"",userType:PARENT,chooseType:"宝宝"}},created:function(){this.userList=JSON.parse(localStorage.getItem("USERLIST")),this.userId=this.userList.list[0].parentId,this.setLocalStorage(this.userList.list[0])},methods:{getUserList:function(){this.$refs.userList.setShowFlags()},getData:function(t){this.userId=t.parentId,this.setLocalStorage(t)},setLocalStorage:function(t){localStorage.setItem("CURRENT_USER",o()(t))}},components:{userList:r.a}}},715:function(t,e,i){e=t.exports=i(499)(),e.push([t.i,".footer{position:fixed;bottom:0;left:0;box-sizing:border-box;z-index:20;padding:.75rem 1.5rem;width:100%;background:#fff;border-top:1px solid #888}.footer .footer-wrapper{display:-webkit-box;display:-webkit-flex;display:flex;text-align:center;font-size:0}.footer .footer-wrapper a.active{color:#ffce87}.footer .footer-wrapper .footer-item{-webkit-box-flex:1;-webkit-flex:1;flex:1;display:block;font-size:1.2rem;color:#888}.footer .footer-wrapper .footer-item a{display:block}.footer .footer-wrapper .footer-item a.router-link-active{color:#ffce87}.footer .footer-wrapper .footer-item a .didi-icons{height:2.25rem;line-height:2.25rem}.footer .footer-wrapper .footer-item a .footer-name{display:block;height:1.25rem;line-height:1.25rem}","",{version:3,sources:["/media/ren/办公/code/web/xiaonuo-vue/src/pages/parent/parent.vue"],names:[],mappings:"AACA,QACE,eAAgB,AAChB,SAAU,AACV,OAAQ,AACR,sBAAuB,AACvB,WAAY,AACZ,sBAAwB,AACxB,WAAY,AACZ,gBAAiB,AACjB,yBAA2B,CAC5B,AACD,wBACE,oBAAqB,AACrB,qBAAsB,AACtB,aAAc,AACd,kBAAmB,AACnB,WAAa,CACd,AACD,iCACE,aAAe,CAChB,AACD,qCACE,mBAAoB,AACpB,eAAgB,AACR,OAAQ,AAChB,cAAe,AACf,iBAAkB,AAClB,UAAY,CACb,AACD,uCACE,aAAe,CAChB,AACD,0DACE,aAAe,CAChB,AACD,mDACE,eAAgB,AAChB,mBAAqB,CACtB,AACD,oDACE,cAAe,AACf,eAAgB,AAChB,mBAAqB,CACtB",file:"parent.vue",sourcesContent:["\n.footer {\n  position: fixed;\n  bottom: 0;\n  left: 0;\n  box-sizing: border-box;\n  z-index: 20;\n  padding: 0.75rem 1.5rem;\n  width: 100%;\n  background: #fff;\n  border-top: 1px solid #888;\n}\n.footer .footer-wrapper {\n  display: -webkit-box;\n  display: -webkit-flex;\n  display: flex;\n  text-align: center;\n  font-size: 0;\n}\n.footer .footer-wrapper a.active {\n  color: #ffce87;\n}\n.footer .footer-wrapper .footer-item {\n  -webkit-box-flex: 1;\n  -webkit-flex: 1;\n          flex: 1;\n  display: block;\n  font-size: 1.2rem;\n  color: #888;\n}\n.footer .footer-wrapper .footer-item a {\n  display: block;\n}\n.footer .footer-wrapper .footer-item a.router-link-active {\n  color: #ffce87;\n}\n.footer .footer-wrapper .footer-item a .didi-icons {\n  height: 2.25rem;\n  line-height: 2.25rem;\n}\n.footer .footer-wrapper .footer-item a .footer-name {\n  display: block;\n  height: 1.25rem;\n  line-height: 1.25rem;\n}"],sourceRoot:""}])},739:function(t,e,i){var s=i(715);"string"==typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);i(500)("3e40647f",s,!0)},768:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("div",{staticClass:"footer"},[i("div",{staticClass:"footer-wrapper"},[i("div",{staticClass:"footer-item"},[i("router-link",{attrs:{to:"/parent/parentIndex"}},[i("i",{staticClass:"didi-icons didi-icons-man"}),i("span",{staticClass:"footer-name"},[t._v("宝宝动态")])])],1),t._v(" "),i("div",{staticClass:"footer-item"},[i("router-link",{attrs:{to:{path:"/parent/report",query:{parentId:t.userId}}}},[i("i",{staticClass:"didi-icons didi-icons-man"}),i("span",{staticClass:"footer-name"},[t._v("宝宝记录")])])],1),t._v(" "),i("div",{staticClass:"footer-item"},[i("router-link",{attrs:{to:{path:"#",query:{parentId:t.userId}}}},[i("i",{staticClass:"didi-icons didi-icons-man"}),i("span",{staticClass:"footer-name"},[t._v("通讯录")])])],1),t._v(" "),i("div",{staticClass:"footer-item"},[i("router-link",{attrs:{to:{path:"#",query:{parentId:t.userId}}}},[i("i",{staticClass:"didi-icons didi-icons-man"}),i("span",{staticClass:"footer-name"},[t._v("小诺平台")])])],1)])]),t._v(" "),i("user-list",{ref:"userList",attrs:{userType:t.userType,chooseType:t.chooseType},on:{userData:t.getData}}),t._v(" "),i("router-view",{attrs:{userId:t.userId,userType:t.userType}})],1)},staticRenderFns:[]}}});
//# sourceMappingURL=14.682a6c0e74648c96dc95.js.map