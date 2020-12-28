<template>
    <view class="container">
        <view class="shuru">
            <form>
                <text>请输入需要定制的酒名</text>
                <input>
            </form>
        </view>
    </view>
</template>

<script>
    export default {
        created() {
            // location.href="https://38482.165183.com/BuildWeb/#/?pagename=index&vv=59"
        },
        data() {
        },
        methods: {
            test: function () {
                location.href = "http://38482.kyk221.cn/BuildWeb/#/pages/product/productdetail?proid=1385714"
            },
        }
    }
</script>

<style>
    .container {
        padding: 20px;
        font-size: 14px;
        line-height: 24px;
    }

    .shuru {
        text-align: center;
    }
</style>
