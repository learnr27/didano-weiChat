webpackJsonp([20],{128:function(t,n,e){"use strict";var a=e(57),i=e(187),o=e(231),c=e(226),s=e(230),r=e(228),u=e(227),d=e(229);a.a.use(i.b),n.a=new i.b.Store({state:o.a,getters:c.a,mutations:s.a,modules:{teacher:r.a,principal:u.a,pickertime:d.a}})},136:function(t,n,e){"use strict";e.d(n,"a",function(){return a}),e.d(n,"b",function(){return i}),e.d(n,"c",function(){return o});var a={isShow:!0,message:"加载中",state:"loading"},i={isShow:!1,state:"success"},o=function(t){return{isShow:!0,state:"error",message:t.data?t.data.message:"网络错误"}}},188:function(t,n,e){"use strict";var a=e(57),i=e(495),o=e(128),c=e(498),s=e.n(c);a.a.use(i.a);var r=new i.a({routes:[{path:"/",redirect:"/role"},{path:"/role",name:"role",component:s.a},{path:"/bind",name:"bind",component:function(){return e.e(15).then(e.bind(null,502))}},{path:"/verify",name:"verify",component:function(){return e.e(11).then(e.bind(null,518))}},{path:"/bindChooseRole",name:"verify",component:function(){return e.e(17).then(e.bind(null,503))}},{path:"/mailContent",name:"mailContent",component:function(){return e.e(18).then(e.bind(null,501))}},{path:"/mailboxList",name:"mailboxList",component:function(){return e.e(0).then(e.bind(null,204))}},{path:"/parent",name:"parent",component:function(){return e.e(14).then(e.bind(null,505))},children:[{path:"/parent/",redirect:"parentIndex"},{path:"/parent/parentIndex",name:"parentIndex",component:function(){return e.e(10).then(e.bind(null,504))}},{path:"/parent/report",name:"report",component:function(){return e.e(16).then(e.bind(null,506))}}]},{path:"/principal",name:"principal",component:function(){return e.e(13).then(e.bind(null,512))},children:[{path:"/principal/",redirect:"principalIndex"},{path:"/principal/principalIndex",name:"principalIndex",component:function(){return e.e(9).then(e.bind(null,511))}},{path:"/principal/mailboxList",name:"mailboxList",component:function(){return e.e(0).then(e.bind(null,204))}},{path:"/principal/attendance",component:function(){return e.e(5).then(e.bind(null,508))},children:[{path:"",name:"PrincipalAttendance",component:function(){return e.e(2).then(e.bind(null,507))}},{path:"studentList",name:"PrincipalStudentList",component:function(){return e.e(7).then(e.bind(null,509))}},{path:"teacherList",name:"PrincipalTeacherList",component:function(){return e.e(6).then(e.bind(null,510))}}]}]},{path:"/teacher",name:"teacher",component:function(){return e.e(12).then(e.bind(null,517))},children:[{path:"/teacher/",redirect:"teacherIndex"},{path:"/teacher/teacherIndex",name:"teacherIndex",component:function(){return e.e(8).then(e.bind(null,516))}},{path:"/teacher/attendance",component:function(){return e.e(4).then(e.bind(null,514))},children:[{path:"",name:"TeacherAttendance",component:function(){return e.e(1).then(e.bind(null,513))}},{path:"studentList",name:"TeacherStudentList",component:function(){return e.e(3).then(e.bind(null,515))}}]}]}]});r.beforeEach(function(t,n,e){o.a.commit("changeLoading",!0),e()}),r.afterEach(function(t){setTimeout(function(){o.a.commit("changeLoading",!1)},200)}),n.a=r},193:function(t,n){},197:function(t,n,e){e(482);var a=e(58)(e(234),e(490),null,null);t.exports=a.exports},198:function(t,n,e){e(486);var a=e(58)(e(235),e(494),null,null);t.exports=a.exports},203:function(t,n,e){e(485);var a=e(58)(e(233),e(493),null,null);t.exports=a.exports},223:function(t,n,e){"use strict";var a=e(57);n.a={SchoolAttendanceFindtByschoolId:function(t,n,e){var i=WX_URL+"/base/attendanc/post/schoolAttendance_findtByschoolId/"+t.schoolId+"/"+t.date;a.a.http.post(i).then(function(t){return n(t)}).catch(function(t){return e(t)})},StaffAttendanceFindtBySchool:function(t,n,e){var i=WX_URL+"/base/attendanc/post/staffAttendance_findtBySchool/"+t.schoolId+"/"+t.date;a.a.http.post(i).then(function(t){return n(t)}).catch(function(t){return e(t)})},StudentAttendanceFindtBySchool:function(t,n,e){var i=WX_URL+"/base/attendanc/post/studentAttendance_findtBySchool/"+t.schoolId+"/"+t.date;a.a.http.post(i).then(function(t){return n(t)}).catch(function(t){return e(t)})}}},224:function(t,n,e){"use strict";var a=e(57);n.a={ClassAttendanceFindtByTeacher:function(t,n,e){var i=WX_URL+"/base/attendanc/post/classAttendance_findtByTeacher/"+t.staffId+"/"+t.date;a.a.http.post(i).then(function(t){return n(t)}).catch(function(t){return e(t)})},StudentAttendanceFindtByClass:function(t,n,e){var i=WX_URL+"/base/attendanc/post/studentAttendance_findtByClass/"+t.classId+"/"+t.date;a.a.http.post(i).then(function(t){return n(t)}).catch(function(t){return e(t)})}}},225:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=e(192),i=e.n(a),o=e(191),c=(e.n(o),e(57)),s=e(194),r=e.n(s),u=e(197),d=e.n(u),l=e(188),h=e(128),f=e(189),p=e(190),m=e(195),g=e.n(m),v=e(196),w=(e.n(v),e(193));e.n(w);c.a.use(g.a,{name:"v-touch"}),c.a.use(f.a),c.a.use(p.a),e.i(v.sync)(h.a,l.a),c.a.http.interceptors.response.use(function(t){return t.data.success?t:i.a.reject(t)},function(t){return i.a.reject(t)}),r.a.attach(document.body),c.a.config.productionTip=!1,new c.a({router:l.a,store:h.a,render:function(t){return t(d.a)}}).$mount("#app-box")},226:function(t,n,e){"use strict";n.a={getLingLoading:function(t){return t.lingLoading},getIsLoading:function(t){return t.isLoading}}},227:function(t,n,e){"use strict";var a=e(223),i=e(136),o={schoolAttendance:{},staffAttendance:{},studentAttendance:{},classesShowContent:[],identity:{31:"园长",32:"老师",33:"医生",34:"后勤"}},c={getSchoolAttendance:function(t){return t.schoolAttendance},getStaffAttendance:function(t){return t.staffAttendance},getStudentAttendanceBySchool:function(t){return t.studentAttendance},getClassesShowContent:function(t){return t.classesShowContent},getIdentity:function(t){return t.identity}},s={actionSchoolAttendance:function(t,n){t.commit("changeLingLoading",i.a),a.a.SchoolAttendanceFindtByschoolId(n,function(n){t.commit("changeLingLoading",i.b),t.commit("mutationSchoolAttendance",n.data.data)},function(n){setTimeout(function(){t.commit("changeLingLoading",e.i(i.c)(n))},200)})},actionStaffAttendance:function(t,n){t.commit("changeLingLoading",i.a),a.a.StaffAttendanceFindtBySchool(n,function(n){t.commit("changeLingLoading",i.b),t.commit("mutationStaffAttendance",n.data.data)},function(n){setTimeout(function(){t.commit("changeLingLoading",e.i(i.c)(n))},200)})},actionStudentAttendanceBySchool:function(t,n){t.commit("changeLingLoading",i.a),a.a.StudentAttendanceFindtBySchool(n,function(n){t.commit("changeLingLoading",i.b),t.commit("mutationStudentAttendance",n.data.data)},function(n){setTimeout(function(){t.commit("changeLingLoading",e.i(i.c)(n))},200)})}},r={mutationSchoolAttendance:function(t,n){t.schoolAttendance=n},mutationStaffAttendance:function(t,n){t.staffAttendance=n},mutationClassesShowContent:function(t,n){t.classesShowContent=n},mutationStudentAttendance:function(t,n){t.studentAttendance=n}};n.a={state:o,getters:c,actions:s,mutations:r}},228:function(t,n,e){"use strict";var a=e(224),i=e(136),o={teacherAttendance:{allNum:0,arriveNum:0,classId:0,noArriveNum:0},studentAttendance:{},teacherAttendanceType:[{state:"迟到",color:"#ff0000"},{state:"早退",color:"#00ffff"},{state:"缺勤",color:"#23fe23"}],teacherAttendanceColor:{1:"#ff0000",2:"#00ffff",3:"#23fe23"}},c={getTeacherAttendance:function(t){return t.teacherAttendance},getStudentAttendance:function(t){return t.studentAttendance},getTeacherAttendanceType:function(t){return t.teacherAttendanceType},getTeacherAttendanceColor:function(t){return t.teacherAttendanceColor}},s={actionTeacherAttendance:function(t,n){t.commit("changeLingLoading",i.a),a.a.ClassAttendanceFindtByTeacher(n,function(n){t.commit("changeLingLoading",i.b),t.commit("mutationTeacherAttendance",n.data.data)},function(n){setTimeout(function(){t.commit("changeLingLoading",e.i(i.c)(n))},200)})},actionStudentAttendance:function(t,n){t.commit("changeLingLoading",i.a),a.a.StudentAttendanceFindtByClass(n,function(n){t.commit("changeLingLoading",i.b),t.commit("mutationStudentAttendance",n.data.data)},function(n){setTimeout(function(){t.commit("changeLingLoading",e.i(i.c)(n))},200)})}},r={mutationTeacherAttendance:function(t,n){t.teacherAttendance=n},mutationStudentAttendance:function(t,n){t.studentAttendance=n}};n.a={state:o,getters:c,actions:s,mutations:r}},229:function(t,n,e){"use strict";var a={years:[],months:[],days:[]},i={getYears:function(t){return t.years},getMonths:function(t){return t.months},getDays:function(t){return t.days}},o={},c={mutationYears:function(t,n){t.years=n},mutationMonths:function(t,n){t.months=n},mutationDays:function(t,n){t.days=n}};n.a={state:a,getters:i,actions:o,mutations:c}},230:function(t,n,e){"use strict";var a=e(137),i=e.n(a);n.a={changeLingLoading:function(t,n){t.lingLoading=i()({},t.lingLoading,n)},changeLoading:function(t,n){t.isLoading=n}}},231:function(t,n,e){"use strict";n.a={lingLoading:{isShow:!0,message:"加载中",state:"loading"},isLoading:!0}},232:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default={name:"loading",model:{prop:"show",event:"change"},props:{show:Boolean,text:String,position:String,transition:{type:String,default:"vux-mask"}},watch:{show:function(t){this.$emit("update:show",t)}}}},233:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=e(238);n.default={name:"toast",mixins:[a.a],props:{value:Boolean,time:{type:Number,default:2e3},type:{type:String,default:"success"},transition:String,width:{type:String,default:"7.6em"},isShowMask:{type:Boolean,default:!1},text:String,position:String},data:function(){return{show:!1}},created:function(){this.value&&(this.show=!0)},computed:{currentTransition:function(){return this.transition?this.transition:"top"===this.position?"vux-slide-from-top":"bottom"===this.position?"vux-slide-from-bottom":"vux-fade"},toastClass:function(){return{"weui-toast_forbidden":"warn"===this.type,"weui-toast_cancel":"cancel"===this.type,"weui-toast_success":"success"===this.type,"weui-toast_text":"text"===this.type,"vux-toast-top":"top"===this.position,"vux-toast-bottom":"bottom"===this.position,"vux-toast-middle":"middle"===this.position}},style:function(){if("text"===this.type&&"auto"===this.width)return{padding:"10px"}}},watch:{show:function(t){var n=this;t&&(this.$emit("input",!0),this.$emit("on-show"),this.fixSafariOverflowScrolling("auto"),clearTimeout(this.timeout),this.timeout=setTimeout(function(){n.show=!1,n.$emit("input",!1),n.$emit("on-hide"),n.fixSafariOverflowScrolling("touch")},this.time))},value:function(t){this.show=t}}}},234:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=e(137),i=e.n(a),o=e(497),c=e.n(o),s=e(187);n.default={name:"app",components:{Loading:c.a},computed:i()({},e.i(s.a)({getIsLoading:"getIsLoading"}))}},235:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default={props:{titleContent:{String:String}}}},236:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=e(201),i=e.n(a),o=e(198),c=e.n(o);n.default={data:function(){return{roleList:[],titleContent:"选择角色"}},created:function(){var t=this;this.classMap=["/super","/org","/school","/parent","/principal","/teacher","/doctor","/service","/assistant"],this.$http.get(WX_URL+"base/login/getRoleSelect/"+openid).then(function(n){n=n.data,n.success&&n.code<2e3?(t.roleList=n.data,1===t.roleList.length&&(localStorage.setItem("USERLIST",i()(t.roleList[0])),t.goToIndex(t.roleList[0].roleType))):n.code>2e3&&(window.location.href="http://test.xiaonuo.cn/wx/?c=bind&a=bindStudent")})},methods:{goToIndex:function(t){this.$router.push(this.classMap[t-27])},selectRole:function(t){localStorage.setItem("USERLIST",i()(t))}},components:{"v-header":c.a}}},482:function(t,n){},483:function(t,n){},484:function(t,n){},485:function(t,n){},486:function(t,n){},490:function(t,n){t.exports={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{attrs:{id:"app"}},[e("router-view"),t._v(" "),e("div",[e("loading",{attrs:{show:t.getIsLoading,"background-color":"#a9a9a9",text:"跳转中"}})],1)],1)},staticRenderFns:[]}},491:function(t,n){t.exports={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("transition",{attrs:{name:t.transition}},[e("div",{directives:[{name:"show",rawName:"v-show",value:t.show,expression:"show"}],staticClass:"weui-loading_toast vux-loading"},[e("div",{staticClass:"weui-mask_transparent"}),t._v(" "),e("div",{staticClass:"weui-toast",style:{position:t.position}},[e("i",{staticClass:"weui-loading weui-icon_toast"}),t._v(" "),e("p",{staticClass:"weui-toast__content"},[t._v(t._s(t.text||"加载中")),t._t("default")],2)])])])},staticRenderFns:[]}},492:function(t,n){t.exports={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{directives:[{name:"show",rawName:"v-show",value:t.roleList.length>1,expression:"roleList.length > 1"}],staticClass:"role"},[e("v-header",{attrs:{titleContent:t.titleContent}}),t._v(" "),e("div",{staticClass:"role-wrapper"},[e("ul",t._l(t.roleList,function(n,a){return e("router-link",{key:1,attrs:{to:t.classMap[n.roleType-27]}},[e("li",{staticClass:"role-item",on:{click:function(e){t.selectRole(n)}}},[t._v(t._s(n.roleName))])])})),t._v(" "),e("router-view")],1)],1)},staticRenderFns:[]}},493:function(t,n){t.exports={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{staticClass:"vux-toast"},[e("div",{directives:[{name:"show",rawName:"v-show",value:t.isShowMask&&t.show,expression:"isShowMask && show"}],staticClass:"weui-mask_transparent"}),t._v(" "),e("transition",{attrs:{name:t.currentTransition}},[e("div",{directives:[{name:"show",rawName:"v-show",value:t.show,expression:"show"}],staticClass:"weui-toast",class:t.toastClass,style:{width:t.width}},[e("i",{directives:[{name:"show",rawName:"v-show",value:"text"!==t.type,expression:"type !== 'text'"}],staticClass:"weui-icon-success-no-circle weui-icon_toast"}),t._v(" "),t.text?e("p",{staticClass:"weui-toast__content",style:t.style,domProps:{innerHTML:t._s(t.text)}}):e("p",{staticClass:"weui-toast__content"},[t._t("default")],2)])])],1)},staticRenderFns:[]}},494:function(t,n){t.exports={render:function(){var t=this,n=t.$createElement;return(t._self._c||n)("div",{staticClass:"header-title"},[t._v("\n  "+t._s(t.titleContent)+"\n")])},staticRenderFns:[]}},497:function(t,n,e){e(483);var a=e(58)(e(232),e(491),null,null);t.exports=a.exports},498:function(t,n,e){e(484);var a=e(58)(e(236),e(492),null,null);t.exports=a.exports}},[225]);
//# sourceMappingURL=app.27d17ebb91ecb105ff2a.js.map