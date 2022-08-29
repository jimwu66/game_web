let app = new Vue({
    el:'#app',
    data:{
        username:'',
        nickname:'',
        email:'',
        birthday:'',
        password:'',
        confirm:'',
        hasError:false,
        message:''
    },
    methods:{
        register:function(){
            console.log('click event');
            let data ={
                username:this.username,
                nickname:this.nickname,
                email:this.email,
                birthday:this.birthday,
                password:this.password,
                confirm:this.confirm,
                hasError:this.hasError,
                message:this.message
            }
            if(this.password != this.confirm){
                this.hasError=true;
                this.message='password mismatch!!';
                console.log("password mismatch!!");
                return;
            }
            let _this = this;
            console.log(data);
            $.ajax({
                url:'/register',
                method:'POST',
                data:data,
                success:function(r){
                    console.log(r);
                    if(r.code==CREATED){
                        _this.hasError=false;
                        console.log('RRRRRRRRRRRRRR');
                        location.href='/login.html?register';
                        console.log('yyyyyyyyyyy');
                    }else if(r.code==UNPROCESABLE_ENTITY){
                        _this.hasError=true;
                        _this.message=r.message;
                        console.log(r.message);
                    }
                }
            });
        }
    }
});