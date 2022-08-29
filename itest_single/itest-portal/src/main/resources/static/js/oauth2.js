let oauth2User = new Vue({
    el:'#oauth2User',
    data:{
        message:''
    },
    methods: {
        loadCurrentUser: function () {
            $.ajax({
                url: '/v1/users/google',
                method: 'POST',
                success: function (r) {
                    console.log(r);
                    if (r.code == OK) {
                        oauth2User.message = r.message;
                        oauth2User.countDown();
                        setTimeout(function () {
                            location.href = '/';
                        }, 2000);
                    } else {
                        alert('can not receive data from server');
                        oauth2User.message = r.message;
                        oauth2User.countDown();
                        setTimeout(function () {
                            location.href = '/login.html?error';
                        }, 2000);
                    }
                }
            });
        },
        countDown: function () {
            let timeleft = 2;
            let downloadTimer = setInterval(function () {
                if (timeleft <= 0) {
                    clearInterval(downloadTimer);
                    document.getElementById("countdown").innerHTML = "Finished";
                } else {
                    document.getElementById("countdown").innerHTML = timeleft + " seconds remaining";
                }
                timeleft -= 0.5;
            }, 500);
        }
    },
    created:function(){
        this.loadCurrentUser();
    }
});