webpackJsonp([11],{489:function(e,t,n){n(688);var i=n(35)(n(657),n(716),null,null);e.exports=i.exports},606:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={methods:{submitted:function(){this.$emit("goVerify")}}}},611:function(e,t,n){t=e.exports=n(470)(),t.push([e.i,".button{position:absolute;left:0;bottom:0;width:100%;height:5rem;line-height:5rem;text-align:center;background:#ffce87;color:#353535;font-size:1.7rem}","",{version:3,sources:["D:/Vue/xiaonuo-vue/src/components/button.vue"],names:[],mappings:"AACA,QACE,kBAAmB,AACnB,OAAQ,AACR,SAAU,AACV,WAAY,AACZ,YAAa,AACb,iBAAkB,AAClB,kBAAmB,AACnB,mBAAoB,AACpB,cAAe,AACf,gBAAkB,CACnB",file:"button.vue",sourcesContent:["\n.button {\n  position: absolute;\n  left: 0;\n  bottom: 0;\n  width: 100%;\n  height: 5rem;\n  line-height: 5rem;\n  text-align: center;\n  background: #ffce87;\n  color: #353535;\n  font-size: 1.7rem;\n}"],sourceRoot:""}])},617:function(e,t,n){var i=n(611);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n(471)("4915224e",i,!0)},624:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement;return(e._self._c||t)("div",{staticClass:"button",on:{click:e.submitted}},[e._v("确定")])},staticRenderFns:[]}},633:function(e,t,n){n(617);var i=n(35)(n(606),n(624),null,null);e.exports=i.exports},639:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={props:{second:{type:Number,default:60}},data:function(){return{time:0,disabled:!0}},methods:{run:function(){this.$emit("run")},start:function(){this.disabled=!1,this.time=this.second,this.timer()},stop:function(){this.time=0},timer:function(){this.time>0?(this.time--,setTimeout(this.timer,1e3)):this.disabled=!0}},computed:{getTips:function(){return this.time>0?this.time+"s后再获取":"获取验证码"}}}},657:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=n(190),o=n.n(i),r=n(186),A=n.n(r),s=n(633),l=n.n(s),a=n(741),u=n.n(a);t.default={data:function(){return{titleContent:"我的宝宝",phoneNumber:"",code:"",showFlag:!0}},methods:{sendCode:function(){this.validatorPhoneNumber(this.phoneNumber)?(this.$refs.timerbtn.start(),this.getTost("验证码已发送，请查收")):this.getTost("手机号码的格式有误")},goVerify:function(){console.log("1111")},getTost:function(e){this.$vux.toast.show({type:"text",position:"middle",text:e})},validatorPhoneNumber:function(e){return/^[0-9]{11}$/.test(e)}},components:{Toast:o.a,"v-header":A.a,"v-button":l.a,"timer-btn":u.a}}},664:function(e,t,n){t=e.exports=n(470)(),t.push([e.i,'.verify-input{padding:0 1.5rem;height:5rem;line-height:5rem;font-size:1.5rem;position:relative}.verify-input:after{display:block;position:absolute;left:0;bottom:0;width:100%;border-top:1px solid #eee;content:" "}.verify-input .phoneNum{width:100%}.verify-div{display:-webkit-box;display:-webkit-flex;display:flex;padding-right:1.5rem;height:5rem;line-height:5rem}.verify-div .verify-code{-webkit-box-flex:1;-webkit-flex:1;flex:1;margin-right:1.5rem;padding-left:1.5rem;position:relative}.verify-div .verify-code:after{display:block;position:absolute;left:0;bottom:0;width:100%;border-top:1px solid #eee;content:" "}.verify-div .verify-code .code{width:100%;border:none;outline:medium;font-size:1.5rem}.verify-div .timerBtn{-webkit-box-flex:0;-webkit-flex:0 0 8rem;flex:0 0 8rem;display:inline-block;margin-top:.5rem;height:4rem;line-height:4rem;border-radius:.5rem;text-align:center;font-size:1.2rem;background:#ffce87}.weui-toast_text{font-size:2.2rem;padding:0 1rem}',"",{version:3,sources:["D:/Vue/xiaonuo-vue/src/pages/verify/verify.vue"],names:[],mappings:"AACA,cACE,iBAAkB,AAClB,YAAa,AACb,iBAAkB,AAClB,iBAAkB,AAClB,iBAAmB,CACpB,AACD,oBACE,cAAe,AACf,kBAAmB,AACnB,OAAQ,AACR,SAAU,AACV,WAAY,AACZ,0BAA2B,AAC3B,WAAa,CACd,AACD,wBACE,UAAY,CACb,AACD,YACE,oBAAqB,AACrB,qBAAsB,AACtB,aAAc,AACd,qBAAsB,AACtB,YAAa,AACb,gBAAkB,CACnB,AACD,yBACE,mBAAoB,AACpB,eAAgB,AACR,OAAQ,AAChB,oBAAqB,AACrB,oBAAqB,AACrB,iBAAmB,CACpB,AACD,+BACE,cAAe,AACf,kBAAmB,AACnB,OAAQ,AACR,SAAU,AACV,WAAY,AACZ,0BAA2B,AAC3B,WAAa,CACd,AACD,+BACE,WAAY,AACZ,YAAa,AACb,eAAgB,AAChB,gBAAkB,CACnB,AACD,sBACE,mBAAoB,AACpB,sBAAuB,AACf,cAAe,AACvB,qBAAsB,AACtB,iBAAmB,AACnB,YAAa,AACb,iBAAkB,AAClB,oBAAsB,AACtB,kBAAmB,AACnB,iBAAkB,AAClB,kBAAoB,CACrB,AACD,iBACE,iBAAkB,AAClB,cAAgB,CACjB",file:"verify.vue",sourcesContent:["\n.verify-input {\n  padding: 0 1.5rem;\n  height: 5rem;\n  line-height: 5rem;\n  font-size: 1.5rem;\n  position: relative;\n}\n.verify-input:after {\n  display: block;\n  position: absolute;\n  left: 0;\n  bottom: 0;\n  width: 100%;\n  border-top: 1px solid #eee;\n  content: ' ';\n}\n.verify-input .phoneNum {\n  width: 100%;\n}\n.verify-div {\n  display: -webkit-box;\n  display: -webkit-flex;\n  display: flex;\n  padding-right: 1.5rem;\n  height: 5rem;\n  line-height: 5rem;\n}\n.verify-div .verify-code {\n  -webkit-box-flex: 1;\n  -webkit-flex: 1;\n          flex: 1;\n  margin-right: 1.5rem;\n  padding-left: 1.5rem;\n  position: relative;\n}\n.verify-div .verify-code:after {\n  display: block;\n  position: absolute;\n  left: 0;\n  bottom: 0;\n  width: 100%;\n  border-top: 1px solid #eee;\n  content: ' ';\n}\n.verify-div .verify-code .code {\n  width: 100%;\n  border: none;\n  outline: medium;\n  font-size: 1.5rem;\n}\n.verify-div .timerBtn {\n  -webkit-box-flex: 0;\n  -webkit-flex: 0 0 8rem;\n          flex: 0 0 8rem;\n  display: inline-block;\n  margin-top: 0.5rem;\n  height: 4rem;\n  line-height: 4rem;\n  border-radius: 0.5rem;\n  text-align: center;\n  font-size: 1.2rem;\n  background: #ffce87;\n}\n.weui-toast_text {\n  font-size: 2.2rem;\n  padding: 0 1rem;\n}"],sourceRoot:""}])},670:function(e,t,n){t=e.exports=n(470)(),t.push([e.i,"","",{version:3,sources:[],names:[],mappings:"",file:"timerBtn.vue",sourceRoot:""}])},688:function(e,t,n){var i=n(664);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n(471)("098a4ad5",i,!0)},694:function(e,t,n){var i=n(670);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n(471)("201f2baf",i,!0)},716:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"verify"},[n("v-header",{attrs:{titleContent:e.titleContent}}),e._v(" "),n("div",{staticClass:"verify-input border-1px"},[n("input",{directives:[{name:"model",rawName:"v-model",value:e.phoneNumber,expression:"phoneNumber"}],staticClass:"phoneNum",attrs:{type:"number",placeholder:"请输入您的电话号码"},domProps:{value:e.phoneNumber},on:{input:function(t){t.target.composing||(e.phoneNumber=t.target.value)}}})]),e._v(" "),n("div",{staticClass:"verify-div"},[e._m(0),e._v(" "),n("timer-btn",{ref:"timerbtn",on:{run:e.sendCode}})],1),e._v(" "),n("v-button",{on:{goVerify:e.goVerify}}),e._v(" "),n("toast",{attrs:{time:800,"is-show-mask":""}})],1)},staticRenderFns:[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"verify-code border-1px"},[n("input",{staticClass:"code",attrs:{type:"number",placeholder:"请输入验证码"}})])}]}},722:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement;return(e._self._c||t)("div",{ref:"timerBtn",staticClass:"timerBtn",on:{click:function(t){e.disabled&&e.run()}}},[e._v("\n  "+e._s(e.getTips)+"\n")])},staticRenderFns:[]}},741:function(e,t,n){n(694);var i=n(35)(n(639),n(722),null,null);e.exports=i.exports}});
//# sourceMappingURL=11.ff824f9d15b722085a04.js.map