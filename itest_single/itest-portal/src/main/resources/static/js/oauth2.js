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
            //v1.2
            const progressText = document.querySelector(".progress_text");
            let progress = 20;

            const loadingBar = setInterval(function () {
                console.log(progress);
                if (progress > 100) {
                    clearInterval(loadingBar);
                } else {
                    console.log(`${progress}%`);
                    progressText.textContent = `${progress}%`;
                    progress += 10;
                }
            }, 200);

            // v1.1
            // let timeleft = 2;
            // let downloadTimer = setInterval(function () {
            //     if (timeleft <= 0) {
            //         clearInterval(downloadTimer);
            //         document.getElementById("countdown").innerHTML = "Finished";
            //     } else {
            //         document.getElementById("countdown").innerHTML = timeleft + " seconds remaining";
            //     }
            //     timeleft -= 0.5;
            // }, 500);
        }
    },
    created:function(){
        this.loadCurrentUser();
    }
});