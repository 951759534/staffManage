/**
 * Created by K550jk on 2017/6/22.
 */
(function(){
    var login = new Vue(
        {
            el:"#loginForm",
            data:function(){
                return{
                        name:"",
                        pass:"",
                        admin: 0,
                        nameValid:true,  //  判断是否第一次进入界面
                        passValid:true,  //  判断是否第一次进入界面
                        nameFlag:false,  //   控制是否满足提交条件
                        passFlag:false,  //   控制是否满足提交条件
                        submitFlag:false,
                        nameContent:'',
                        passContent:'格式错误'
                }
            },
            methods:{
                changeName:function(){
                    this.nameValid=false;
                    if(this.name.length !== 0){
                        this.nameFlag = true;
                        this.nameContent = "";
                    }else{
                        this.nameFlag = false;
                        this.nameContent= "格式错误"
                    }
                },
                changePass:function(){
                    this.passValid=false;
                    if(this.pass.length !==0 && !/\?|=/g.test(this.pass)){
                        this.passFlag = true
                    }else{
                        this.passFlag = false
                        this.passContent = "格式错误"
                    }
                },
                login:function(e){
                    this.nameValid=false;
                    this.passValid=false;
                    var _this = this;
                    if(this.nameFlag&&this.passFlag){
                        e.preventDefault();
                        this.$http.post("/checkLogin", {
                                    'name':this.name,
                                    'pass':this.pass,
                                    'admin':this.admin
                            }).then(function(res){
                            var result = res.bodyText;
                            if(result == "success"){
                                location.href = _this.getUrl(_this.admin);
                            }else{

                                _this.$http.post("/checkUser",{'name':_this.name,'admin':_this.admin}).then(function(res){
                                   if(res.bodyText === "have"){
                                       _this.passFlag = false;
                                       _this.passContent = "密码错误"
                                       _this.nameFlag = true;
                                       _this.nameContent = ""
                                   }else{
                                       _this.nameFlag = false;
                                       _this.nameContent = "用户不存在"
                                       _this.passFlag = true;
                                       _this.passContent = ""
                                   }
                                });
                            }
                        }).catch(function(res){
                        })
                    }
                },
                getUrl:function(data){
                    switch (data){
                        case '0':return 'staff/index';
                            break;
                        case '1': return 'admin/index';
                            break;
                        default:return '/'
                    }
                }
            }
        }
    )
})();