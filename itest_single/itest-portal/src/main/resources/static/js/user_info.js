let userInfo = new Vue({
    el:'#user_info',
    data: {
      user:{},
    },
    methods: {
        loadCurrentUser:function(){
            $.ajax({
                url:'/v1/users/me',
                method:'POST',
                success:function(r){
                    console.log(r);
                    if(r.code==OK){
                        userInfo.user=r.data;
                        if(userInfo.user.dailyLogin){
                            Swal.fire(
                                'Daily login award!',
                                'You got 10 coins!',
                                'success'
                            )
                        }
                    }else{
                        alert('can not receive data from server');
                        r.message;
                    }
                }
            });
        },
        changeIcon:function(nickname,productName,productTitle){
            console.log("change icon to "+productName);
            $.ajax({
                url:'/v1/users/icon/',
                method:'GET',
                data:{
                    nickname:nickname,
                    productName:productName,
                    productTitle:productTitle
                },
                success:function (r){
                    console.log(r)
                    if(r.code==OK){
                        Swal.fire({
                            title: 'Icon changed!',
                            text: 'Change icon/title to '+productName,
                            imageUrl: 'img/user/'+productName+'.png',
                            imageWidth: 80,
                            imageHeight: 80,
                            imageAlt: 'Custom image',
                        })
                        userInfo.user.level=productName;
                        userInfo.user.title=productTitle;
                    }else{
                        console.log(r.message)
                    }
                }
            });
        }
    },

    created:function(){
        this.loadCurrentUser();
    }
});
