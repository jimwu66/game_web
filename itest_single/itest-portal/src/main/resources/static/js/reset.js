let resetApp = new Vue({
    el:'#resetApp',
    data:{
        password:'',
        confirmed:'',
        message:'',
        hasError:false
    },
    methods:{
        reset:function(){
            let token = location.search.substring(7)
            if(this.password!=this.confirmed){
                this.hasError = true
                this.message = 'please confirm password again';
                return
            }
            $.ajax({
                url:'/reset',
                method:'POST',
                data:{
                    token,
                    password:this.password
                },
                success:function(r){
                    console.log(r);
                    if(r.code === OK){
                        resetApp.hasError = false
                        location.href='/login.html?reset';
                    }else{
                        resetApp.hasError = true
                        resetApp.message = r.message
                    }
                }
            });
        }
    }
});