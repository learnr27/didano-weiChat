webpackJsonp([13],{487:function(e,t,n){n(720);var i=n(35)(n(675),n(754),null,null);e.exports=i.exports},550:function(e,t,n){n(561);var i=n(35)(n(556),n(564),null,null);e.exports=i.exports},555:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=n(188),s=n.n(i),o=n(187),a=n.n(o);t.default={name:"actionsheet",mounted:function(){var e=this;this.hasHeaderSlot=!!this.$slots.header,this.$nextTick(function(){e.$tabbar=document.querySelector(".weui-tabbar")})},props:{value:Boolean,showCancel:Boolean,cancelText:String,theme:{type:String,default:"ios"},menus:{type:[Object,Array],default:function(){return{}}},closeOnClickingMask:{type:Boolean,default:!0},closeOnClickingMenu:{type:Boolean,default:!0}},data:function(){return{hasHeaderSlot:!1,show:!1}},methods:{onMenuClick:function(e,t){"string"==typeof e?this.emitEvent("on-click-menu",t,e):"disabled"!==e.type&&"info"!==e.type&&(e.value?this.emitEvent("on-click-menu",e.value,e):(this.emitEvent("on-click-menu","",e),this.show=!1))},onClickingMask:function(){this.$emit("on-click-mask"),this.closeOnClickingMask&&(this.show=!1)},emitEvent:function(e,t,n){if("on-click-menu"===e&&!/.noop/.test(t)){var i=n;"object"===(void 0===i?"undefined":a()(i))&&(i=JSON.parse(s()(i))),this.$emit(e,t,i),this.$emit(e+"-"+t),this.closeOnClickingMenu&&(this.show=!1)}},fixIos:function(e){this.$el.parentNode&&-1!==this.$el.parentNode.className.indexOf("v-transfer-dom")||this.$tabbar&&/iphone/i.test(navigator.userAgent)&&(this.$tabbar.style.zIndex=e)}},watch:{show:function(e){var t=this;this.$emit("input",e),e?this.fixIos(-1):setTimeout(function(){t.fixIos(100)},200)},value:{handler:function(e){this.show=e},immediate:!0}},beforeDestroy:function(){this.fixIos(100)}}},556:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={props:{tipsContent:{type:String}}}},558:function(e,t,n){t=e.exports=n(470)(),t.push([e.i,".tips{display:-webkit-box;display:-webkit-flex;display:flex;width:80%;margin:28px auto 24px}.tips .line{-webkit-box-flex:1;-webkit-flex:1;flex:1;position:relative;top:-6px;border-bottom:2px solid #ffce87}.tips .text{padding:0 12px;color:#7e8c8d;font-size:14px;font-weight:700}","",{version:3,sources:["D:/Vue/xiaonuo-vue/src/components/emptyTips.vue"],names:[],mappings:"AACA,MACE,oBAAqB,AACrB,qBAAsB,AACtB,aAAc,AACd,UAAW,AACX,qBAA4B,CAC7B,AACD,YACE,mBAAoB,AACpB,eAAgB,AACR,OAAQ,AAChB,kBAAmB,AACnB,SAAU,AACV,+BAAiC,CAClC,AACD,YACE,eAAgB,AAChB,cAAe,AACf,eAAgB,AAChB,eAAiB,CAClB",file:"emptyTips.vue",sourcesContent:["\n.tips {\n  display: -webkit-box;\n  display: -webkit-flex;\n  display: flex;\n  width: 80%;\n  margin: 28px auto 24px auto;\n}\n.tips .line {\n  -webkit-box-flex: 1;\n  -webkit-flex: 1;\n          flex: 1;\n  position: relative;\n  top: -6px;\n  border-bottom: 2px solid #ffce87;\n}\n.tips .text {\n  padding: 0 12px;\n  color: #7e8c8d;\n  font-size: 14px;\n  font-weight: 700;\n}"],sourceRoot:""}])},560:function(e,t,n){t=e.exports=n(470)(),t.push([e.i,'.weui-mask{background:rgba(0,0,0,.6)}.weui-mask,.weui-mask_transparent{position:fixed;z-index:1000;top:0;right:0;left:0;bottom:0}.weui-actionsheet{position:fixed;left:0;bottom:0;-webkit-transform:translateY(100%);transform:translateY(100%);-webkit-backface-visibility:hidden;backface-visibility:hidden;z-index:5000;width:100%;background-color:#efeff4;-webkit-transition:-webkit-transform .3s;transition:-webkit-transform .3s;transition:transform .3s;transition:transform .3s,-webkit-transform .3s}.weui-actionsheet__menu{background-color:#fff}.weui-actionsheet__action{margin-top:6px;background-color:#fff}.weui-actionsheet__cell{position:relative;padding:10px 0;text-align:center;font-size:18px}.weui-actionsheet__cell:before{content:" ";position:absolute;left:0;top:0;right:0;height:1px;border-top:1px solid #d9d9d9;color:#d9d9d9;-webkit-transform-origin:0 0;transform-origin:0 0;-webkit-transform:scaleY(.5);transform:scaleY(.5)}.weui-actionsheet__cell:active{background-color:#ececec}.weui-actionsheet__cell:first-child:before{display:none}.weui-skin_android .weui-actionsheet{position:fixed;left:50%;top:50%;bottom:auto;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%);width:274px;box-sizing:border-box;-webkit-backface-visibility:hidden;backface-visibility:hidden;background:transparent;-webkit-transition:-webkit-transform .3s;transition:-webkit-transform .3s;transition:transform .3s;transition:transform .3s,-webkit-transform .3s}.weui-skin_android .weui-actionsheet__action{display:none}.weui-skin_android .weui-actionsheet__menu{border-radius:2px;box-shadow:0 6px 30px 0 rgba(0,0,0,.1)}.weui-skin_android .weui-actionsheet__cell{padding:13px 24px;font-size:16px;line-height:1.4;text-align:left}.weui-skin_android .weui-actionsheet__cell:first-child{border-top-left-radius:2px;border-top-right-radius:2px}.weui-skin_android .weui-actionsheet__cell:last-child{border-bottom-left-radius:2px;border-bottom-right-radius:2px}.weui-actionsheet_toggle{-webkit-transform:translate(0);transform:translate(0)}.vux-actionsheet-menu-primary{color:#1aad19}.vux-actionsheet-menu-warn{color:#e64340}.vux-actionsheet-menu-default{color:#000}.vux-actionsheet-menu-disabled{color:#ccc}.vux-actionsheet-mask-enter,.vux-actionsheet-mask-leave-active,.vux-android-actionsheet-enter,.vux-android-actionsheet-leave-active{opacity:0}.vux-actionsheet-mask-enter-active,.vux-actionsheet-mask-leave-active,.vux-android-actionsheet-enter-active,.vux-android-actionsheet-leave-active{-webkit-transition:opacity .3s!important;transition:opacity .3s!important}',"",{version:3,sources:["D:/Vue/xiaonuo-vue/node_modules/vux/src/components/actionsheet/index.vue"],names:[],mappings:"AA0GA,WAOE,yBAA+B,CAChC,AACD,kCARE,eAAgB,AAChB,aAAc,AACd,MAAO,AACP,QAAS,AACT,OAAQ,AACR,QAAU,CAUX,AACD,kBACE,eAAgB,AAChB,OAAQ,AACR,SAAU,AACV,mCAAsC,AAC9B,2BAA8B,AACtC,mCAAoC,AAC5B,2BAA4B,AACpC,aAAc,AACd,WAAY,AACZ,yBAA0B,AAC1B,yCAA0C,AAC1C,iCAAkC,AAClC,yBAA0B,AAC1B,8CAAiD,CAClD,AACD,wBACE,qBAA0B,CAC3B,AACD,0BACE,eAAgB,AAChB,qBAA0B,CAC3B,AACD,wBACE,kBAAmB,AACnB,eAAgB,AAChB,kBAAmB,AACnB,cAAgB,CACjB,AACD,+BACE,YAAa,AACb,kBAAmB,AACnB,OAAQ,AACR,MAAO,AACP,QAAS,AACT,WAAY,AACZ,6BAA8B,AAC9B,cAAe,AACf,6BAA8B,AACtB,qBAAsB,AAC9B,6BAA+B,AACvB,oBAAuB,CAChC,AACD,+BACE,wBAA0B,CAC3B,AACD,2CACE,YAAc,CACf,AACD,qCACE,eAAgB,AAChB,SAAU,AACV,QAAS,AACT,YAAa,AACb,uCAAyC,AACjC,+BAAiC,AACzC,YAAa,AACb,sBAAuB,AACvB,mCAAoC,AAC5B,2BAA4B,AACpC,uBAAwB,AACxB,yCAA0C,AAC1C,iCAAkC,AAClC,yBAA0B,AAC1B,8CAAiD,CAClD,AACD,6CACE,YAAc,CACf,AACD,2CACE,kBAAmB,AACnB,sCAA4C,CAC7C,AACD,2CACE,kBAAmB,AACnB,eAAgB,AAChB,gBAAiB,AACjB,eAAiB,CAClB,AACD,uDACE,2BAA4B,AAC5B,2BAA6B,CAC9B,AACD,sDACE,8BAA+B,AAC/B,8BAAgC,CACjC,AACD,yBACE,+BAAmC,AAC3B,sBAA2B,CACpC,AACD,8BACE,aAAe,CAChB,AACD,2BACE,aAAe,CAChB,AACD,8BACE,UAAY,CACb,AACD,+BACE,UAAY,CACb,AACD,oIAIE,SAAW,CACZ,AACD,kJAIE,yCAA4C,AAC5C,gCAAoC,CACrC",file:"index.vue",sourcesContent:['/**\n* actionsheet\n*/\n/**\n* datetime\n*/\n/**\n* tabbar\n*/\n/**\n* tab\n*/\n/**\n* dialog\n*/\n/**\n* x-number\n*/\n/**\n* checkbox\n*/\n/**\n* check-icon\n*/\n/**\n* Cell\n*/\n/**\n* Mask\n*/\n/**\n* Range\n*/\n/**\n* Tabbar\n*/\n/**\n* Header\n*/\n/**\n* Timeline\n*/\n/**\n* Switch\n*/\n/**\n* Button\n*/\n/**\n* swipeout\n*/\n/**\n* Cell\n*/\n/**\n* Badge\n*/\n/**\n* Popover\n*/\n/**\n* Button tab\n*/\n/* alias */\n/**\n* Swiper\n*/\n/**\n* checklist\n*/\n/**\n* popup-picker\n*/\n/**\n* popup\n*/\n/**\n* popup-header\n*/\n/**\n* form-preview\n*/\n/**\n* load-more\n*/\n/**\n* sticky\n*/\n/**\n* group\n*/\n/**\n* toast\n*/\n/**\n* icon\n*/\n/**\n* calendar\n*/\n/**\n* search\n*/\n/**\n* radio\n*/\n.weui-mask {\n  position: fixed;\n  z-index: 1000;\n  top: 0;\n  right: 0;\n  left: 0;\n  bottom: 0;\n  background: rgba(0, 0, 0, 0.6);\n}\n.weui-mask_transparent {\n  position: fixed;\n  z-index: 1000;\n  top: 0;\n  right: 0;\n  left: 0;\n  bottom: 0;\n}\n.weui-actionsheet {\n  position: fixed;\n  left: 0;\n  bottom: 0;\n  -webkit-transform: translate(0, 100%);\n          transform: translate(0, 100%);\n  -webkit-backface-visibility: hidden;\n          backface-visibility: hidden;\n  z-index: 5000;\n  width: 100%;\n  background-color: #EFEFF4;\n  -webkit-transition: -webkit-transform .3s;\n  transition: -webkit-transform .3s;\n  transition: transform .3s;\n  transition: transform .3s, -webkit-transform .3s;\n}\n.weui-actionsheet__menu {\n  background-color: #FFFFFF;\n}\n.weui-actionsheet__action {\n  margin-top: 6px;\n  background-color: #FFFFFF;\n}\n.weui-actionsheet__cell {\n  position: relative;\n  padding: 10px 0;\n  text-align: center;\n  font-size: 18px;\n}\n.weui-actionsheet__cell:before {\n  content: " ";\n  position: absolute;\n  left: 0;\n  top: 0;\n  right: 0;\n  height: 1px;\n  border-top: 1px solid #D9D9D9;\n  color: #D9D9D9;\n  -webkit-transform-origin: 0 0;\n          transform-origin: 0 0;\n  -webkit-transform: scaleY(0.5);\n          transform: scaleY(0.5);\n}\n.weui-actionsheet__cell:active {\n  background-color: #ECECEC;\n}\n.weui-actionsheet__cell:first-child:before {\n  display: none;\n}\n.weui-skin_android .weui-actionsheet {\n  position: fixed;\n  left: 50%;\n  top: 50%;\n  bottom: auto;\n  -webkit-transform: translate(-50%, -50%);\n          transform: translate(-50%, -50%);\n  width: 274px;\n  box-sizing: border-box;\n  -webkit-backface-visibility: hidden;\n          backface-visibility: hidden;\n  background: transparent;\n  -webkit-transition: -webkit-transform .3s;\n  transition: -webkit-transform .3s;\n  transition: transform .3s;\n  transition: transform .3s, -webkit-transform .3s;\n}\n.weui-skin_android .weui-actionsheet__action {\n  display: none;\n}\n.weui-skin_android .weui-actionsheet__menu {\n  border-radius: 2px;\n  box-shadow: 0 6px 30px 0 rgba(0, 0, 0, 0.1);\n}\n.weui-skin_android .weui-actionsheet__cell {\n  padding: 13px 24px;\n  font-size: 16px;\n  line-height: 1.4;\n  text-align: left;\n}\n.weui-skin_android .weui-actionsheet__cell:first-child {\n  border-top-left-radius: 2px;\n  border-top-right-radius: 2px;\n}\n.weui-skin_android .weui-actionsheet__cell:last-child {\n  border-bottom-left-radius: 2px;\n  border-bottom-right-radius: 2px;\n}\n.weui-actionsheet_toggle {\n  -webkit-transform: translate(0, 0);\n          transform: translate(0, 0);\n}\n.vux-actionsheet-menu-primary {\n  color: #1AAD19;\n}\n.vux-actionsheet-menu-warn {\n  color: #E64340;\n}\n.vux-actionsheet-menu-default {\n  color: #000;\n}\n.vux-actionsheet-menu-disabled {\n  color: #ccc;\n}\n.vux-actionsheet-mask-enter,\n.vux-actionsheet-mask-leave-active,\n.vux-android-actionsheet-enter,\n.vux-android-actionsheet-leave-active {\n  opacity: 0;\n}\n.vux-actionsheet-mask-leave-active,\n.vux-actionsheet-mask-enter-active,\n.vux-android-actionsheet-leave-active,\n.vux-android-actionsheet-enter-active {\n  -webkit-transition: opacity 300ms!important;\n  transition: opacity 300ms!important;\n}\n'],sourceRoot:""}])},561:function(e,t,n){var i=n(558);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n(471)("0c793175",i,!0)},563:function(e,t,n){var i=n(560);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n(471)("4915cd4f",i,!0)},564:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"tips"},[n("div",{staticClass:"line"}),e._v(" "),n("div",{staticClass:"text"},[e._v(e._s(e.tipsContent))]),e._v(" "),n("div",{staticClass:"line"})])},staticRenderFns:[]}},566:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"vux-actionsheet"},[n("transition",{attrs:{name:"vux-actionsheet-mask"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:e.show,expression:"show"}],staticClass:"weui-mask weui-mask_transparent",on:{click:e.onClickingMask}})]),e._v(" "),"android"===e.theme?n("div",{staticClass:"weui-skin_android"},[n("transition",{attrs:{name:"vux-android-actionsheet"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:e.show,expression:"show"}],staticClass:"weui-actionsheet"},[n("div",{staticClass:"weui-actionsheet__menu"},e._l(e.menus,function(t,i){return n("div",{staticClass:"weui-actionsheet__cell",domProps:{innerHTML:e._s(t.label||t)},on:{click:function(n){e.onMenuClick(t,i)}}})}))])])],1):n("div",{staticClass:"weui-actionsheet",class:{"weui-actionsheet_toggle":e.show}},[n("div",{staticClass:"weui-actionsheet__menu"},[e.hasHeaderSlot?n("div",{staticClass:"weui-actionsheet__cell"},[e._t("header")],2):e._e(),e._v(" "),e._l(e.menus,function(t,i){return n("div",{staticClass:"weui-actionsheet__cell",class:"vux-actionsheet-menu-"+(t.type||"default"),domProps:{innerHTML:e._s(t.label||t)},on:{click:function(n){e.onMenuClick(t,i)}}})})],2),e._v(" "),e.showCancel?n("div",{staticClass:"weui-actionsheet__action",on:{click:function(t){e.emitEvent("on-click-menu","cancel")}}},[n("div",{staticClass:"weui-actionsheet__cell"},[e._v(e._s(e.cancelText||"取消"))])]):e._e()])],1)},staticRenderFns:[]}},567:function(e,t,n){n(563);var i=n(35)(n(555),n(566),null,null);e.exports=i.exports},675:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=n(567),s=n.n(i),o=n(186),a=n.n(o),A=n(550),r=n.n(A),l=n(185),c=n.n(l);t.default={props:{userId:{Number:Number,default:0},userType:{Number:Number,default:0}},data:function(){return{showTips:0,mailBoxList:[],titleContent:"园长邮箱",defaultAvatar:"http://cdn-didano.oss-cn-shenzhen.aliyuncs.com/xiaonuo/wx/img/logo_2.jpg",state:{isShow:!0,message:"加载中",state:"loading"},sheetShow:!1,sheetMenus:{menu1:"确定要删除该消息？",menu2:'<span style="color:red">删除</span>'},deleteMessageId:"",selectItem:-1}},created:function(){this._initMailBox()},methods:{_initMailBox:function(){var e=this;this.$http.post(WX_URL+"/base/mailbox/post/mail_findtByBossId/"+this.userId+"/"+this.userType).then(function(t){t=t.data,t.success&&(e.state.isShow=!1,e.mailBoxList=t.data,e.mailBoxList.list.length?e.showTips=0:e.showTips=1)},function(t){e.state.state="error",e.state.message="网络错误"})},isRead:function(e,t){var n=this;this.$http.post(WX_URL+"base/notice/post/setNoticeRead",{noticeId:e,userId:this.userId}).then(function(e){e=e.data,e.success?n.mailBoxList.list[t].is_read=1:console.log("code:1002")})},selectMail:function(e,t){e.is_read||this.isRead(e.id,t),this.$router.push({path:"/mailContent",query:{mailId:e.sourceId,dialogueTitle:"与"+e.title+"进行沟通"}})},deleteMessage:function(e){this.sheetShow=!0,this.deleteMessageId=e.id},SheetClick:function(e){var t=this;"menu2"===e&&this.$http.post(WX_URL+"base/notice/post/deleteNoticeByUser",{noticeId:this.deleteMessageId,userId:this.userId}).then(function(e){e=e.data,e.success&&t._initMailBox()})},fetchData:function(){this._initMailBox()}},components:{"v-header":a.a,LingLoading:c.a,emptyTips:r.a,Actionsheet:s.a}}},690:function(e,t,n){t=e.exports=n(470)(),t.push([e.i,'.mailboxList{margin-bottom:7rem}.mailboxList .mailbox-list{display:-webkit-box;display:-webkit-flex;display:flex;box-sizing:border-box;padding:1.5rem;width:100%;position:relative}.mailboxList .mailbox-list:after{display:block;position:absolute;left:0;bottom:0;width:100%;border-top:1px solid #eee;content:" "}.mailboxList .mailbox-list .avatar{-webkit-box-flex:0;-webkit-flex:0 0 6rem;flex:0 0 6rem;height:6rem}.mailboxList .mailbox-list .avatar img{width:6rem;height:6rem;border-radius:2px}.mailboxList .mailbox-list .mail-des{-webkit-box-flex:1;-webkit-flex:1;flex:1;padding-left:.75rem}.mailboxList .mailbox-list .mail-des .mail-user{height:3rem;line-height:3rem;font-size:1.7rem;color:#353535}.mailboxList .mailbox-list .mail-des .mail-news{height:3rem;line-height:3rem;font-size:1.5rem;color:#a9a9a9}.mailboxList .mailbox-list .new-message{-webkit-box-flex:0;-webkit-flex:0 0 3rem;flex:0 0 3rem;height:6rem;line-height:6rem}.mailboxList .mailbox-list .new-message span{display:inline-block;width:1rem;height:1rem;border-radius:50%;background:red;color:#fff}',"",{version:3,sources:["D:/Vue/xiaonuo-vue/src/pages/principal/mailbox/mailboxList.vue"],names:[],mappings:"AACA,aACE,kBAAoB,CACrB,AACD,2BACE,oBAAqB,AACrB,qBAAsB,AACtB,aAAc,AACd,sBAAuB,AACvB,eAAgB,AAChB,WAAY,AACZ,iBAAmB,CACpB,AACD,iCACE,cAAe,AACf,kBAAmB,AACnB,OAAQ,AACR,SAAU,AACV,WAAY,AACZ,0BAA2B,AAC3B,WAAa,CACd,AACD,mCACE,mBAAoB,AACpB,sBAAuB,AACf,cAAe,AACvB,WAAa,CACd,AACD,uCACE,WAAY,AACZ,YAAa,AACb,iBAAmB,CACpB,AACD,qCACE,mBAAoB,AACpB,eAAgB,AACR,OAAQ,AAChB,mBAAsB,CACvB,AACD,gDACE,YAAa,AACb,iBAAkB,AAClB,iBAAkB,AAClB,aAAe,CAChB,AACD,gDACE,YAAa,AACb,iBAAkB,AAClB,iBAAkB,AAClB,aAAe,CAChB,AACD,wCACE,mBAAoB,AACpB,sBAAuB,AACf,cAAe,AACvB,YAAa,AACb,gBAAkB,CACnB,AACD,6CACE,qBAAsB,AACtB,WAAY,AACZ,YAAa,AACb,kBAAmB,AACnB,eAAiB,AACjB,UAAY,CACb",file:"mailboxList.vue",sourcesContent:["\n.mailboxList {\n  margin-bottom: 7rem;\n}\n.mailboxList .mailbox-list {\n  display: -webkit-box;\n  display: -webkit-flex;\n  display: flex;\n  box-sizing: border-box;\n  padding: 1.5rem;\n  width: 100%;\n  position: relative;\n}\n.mailboxList .mailbox-list:after {\n  display: block;\n  position: absolute;\n  left: 0;\n  bottom: 0;\n  width: 100%;\n  border-top: 1px solid #eee;\n  content: ' ';\n}\n.mailboxList .mailbox-list .avatar {\n  -webkit-box-flex: 0;\n  -webkit-flex: 0 0 6rem;\n          flex: 0 0 6rem;\n  height: 6rem;\n}\n.mailboxList .mailbox-list .avatar img {\n  width: 6rem;\n  height: 6rem;\n  border-radius: 2px;\n}\n.mailboxList .mailbox-list .mail-des {\n  -webkit-box-flex: 1;\n  -webkit-flex: 1;\n          flex: 1;\n  padding-left: 0.75rem;\n}\n.mailboxList .mailbox-list .mail-des .mail-user {\n  height: 3rem;\n  line-height: 3rem;\n  font-size: 1.7rem;\n  color: #353535;\n}\n.mailboxList .mailbox-list .mail-des .mail-news {\n  height: 3rem;\n  line-height: 3rem;\n  font-size: 1.5rem;\n  color: #a9a9a9;\n}\n.mailboxList .mailbox-list .new-message {\n  -webkit-box-flex: 0;\n  -webkit-flex: 0 0 3rem;\n          flex: 0 0 3rem;\n  height: 6rem;\n  line-height: 6rem;\n}\n.mailboxList .mailbox-list .new-message span {\n  display: inline-block;\n  width: 1rem;\n  height: 1rem;\n  border-radius: 50%;\n  background: #f00;\n  color: #fff;\n}"],sourceRoot:""}])},720:function(e,t,n){var i=n(690);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n(471)("107e8971",i,!0)},754:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("v-header",{attrs:{titleContent:e.titleContent}}),e._v(" "),n("div",{staticClass:"mailboxList"},[n("div",{staticClass:"mailboxList-wrapper"},[n("ul",e._l(e.mailBoxList.list,function(t,i){return n("v-touch",{key:t.id,staticClass:"mailbox-list border-1px",attrs:{tag:"li"},on:{tap:function(n){e.selectMail(t,i)},press:function(n){e.deleteMessage(t)}}},[n("div",{staticClass:"avatar"},[""!=t.headUrl?n("img",{attrs:{src:t.headUrl}}):n("img",{attrs:{src:e.defaultAvatar}})]),e._v(" "),n("div",{staticClass:"mail-des"},[n("div",{staticClass:"mail-user"},[e._v(e._s(t.title))]),e._v(" "),n("div",{directives:[{name:"show",rawName:"v-show",value:!t.is_read,expression:"!mailItem.is_read"}],staticClass:"mail-news"},[e._v(e._s(t.senderName)+"发来的消息")])]),e._v(" "),n("div",{staticClass:"new-message"},[n("span",{directives:[{name:"show",rawName:"v-show",value:!t.is_read,expression:"!mailItem.is_read"}]})])])}))]),e._v(" "),n("empty-tips",{directives:[{name:"show",rawName:"v-show",value:e.showTips,expression:"showTips"}],attrs:{tipsContent:"暂无消息"}}),e._v(" "),n("ling-loading",{attrs:{click:e.fetchData,state:e.state}}),e._v(" "),n("actionsheet",{attrs:{menus:e.sheetMenus,"show-cancel":""},on:{"on-click-menu":e.SheetClick},model:{value:e.sheetShow,callback:function(t){e.sheetShow=t},expression:"sheetShow"}})],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=13.27875bfcbe3d3740c38c.js.map