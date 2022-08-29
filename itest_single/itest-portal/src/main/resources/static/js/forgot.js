let forgotApp = new Vue({
    el:'#forgotApp',
    data:{
        email:'',
        message:''
    },
    methods:{
        resetRequest:function(){
            $(".button").toggleClass("button--loading")
            let data = {
                    email:this.email
                }
            $.ajax({
                url:'/resetRequest',
                method:'POST',
                data:data,
                success:function(r){
                    $(".button").toggleClass("button--loading")
                    console.log(r);
                    if(r.code === OK){
                        $('#message').css('color','green')
                        forgotApp.message=r.message
                    }else{
                        $('#message').css('color','red')
                        forgotApp.message=r.message
                    }
                }
            });
        }
    }
});