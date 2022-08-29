let coinApp = new Vue({
    el: '#coinApp',
    data: {
        coin: ''
    },
    methods: {
        loadCoin: function () {
            let nickname = document.getElementById('nickname').innerText
            console.log(nickname)
            $.ajax({
                url: '/v1/coin/' + nickname,
                method: 'GET',
                success: function (r) {
                    console.log(r);
                    if (r.code == OK) {
                        r.message;
                    } else {
                        r.message;
                    }
                }
            });
        }
    },
    updated:function(){
        this.loadCoin();
    }
});
