let productApp = new Vue({
    el: '#productApp',
    data: {
        products: []
    },
    methods: {
        loadProducts: function () {
            $.ajax({
                url: '/v1/shop/recommend',
                method: 'GET',
                success: function (r) {
                    console.log(r);
                    if (r.code == OK) {
                        console.log(r.message);
                        productApp.products = r.data;
                    } else {
                        console.log(r.message);
                    }
                }
            });
        },
        purchase: function (productName,productTitle,price) {
            let userCoin = userInfo.user.coin
            let nickname = userInfo.user.nickname
            console.log("your coin is " + userCoin)
            if (price > userCoin) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Insufficient coin(' + userCoin + ')',
                })
            }
            Swal.fire({
                title: 'Are you sure?',
                text: 'are you sure buy\n' + productName + ' for ' + price + ' coins?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, purchase it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        url: '/v1/users/buy',
                        method: 'GET',
                        data: {
                            productName: productName,
                            productTitle: productTitle,
                            price: price,
                            nickname: nickname
                        },
                        success: function (r) {
                            console.log(r)
                            if (r.code == OK) {
                                userInfo.user.coin = r.data.coin;
                                userInfo.user.products = r.data.products
                                console.log(r.message)
                                Swal.fire(
                                    'Purchased!',
                                    productName + ' has been added to your profile.',
                                    'success'
                                )
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Oops...',
                                    text: r.message,
                                })
                            }
                        }
                    });
                } else {
                    return
                }
            })
        }
    },
    created: function () {
        this.loadProducts();
    }
});