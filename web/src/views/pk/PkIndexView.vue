<template>
    <PlayGround v-if="$store.state.pk.status === 'playing'" />
    <MatchGround v-if="$store.state.pk.status === 'matching'" />
</template>

<script>
import PlayGround from "@/components/PlayGround";
import MatchGround from "@/components/MatchGround.vue";
import { onMounted, onUnmounted } from 'vue'; // onMounted表示挂载，onUnmounted表示未挂载，即是否有打开当前界面
import { useStore } from 'vuex';

export default {
    components: {
        PlayGround,
        MatchGround,
    },
    setup() {
        const store = new useStore();
        const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}`;

        let socket = null;
        onMounted(() => {
            store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
            socket = new WebSocket(socketUrl); // WebSocket方法在js中自带

            socket.onopen = () => {
                console.log("connected!");
                store.commit("updateSocket", socket);
            }

            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                console.log(data);
                if (data.event === "start-matching") {
                    store.commit("updateOpponent", {
                        username: data.opponent_username,
                        photo: data.opponent_photo,
                    });
                    setTimeout(() => {
                        store.commit("updateStatus", "playing");
                    }, 2000);
                    store.commit("updateGamemap", data.gamemap);
                }
            }

            socket.onclose = () => {
                console.log("disconnected!");
            }
        });

        onUnmounted(() => {
            socket.close();
        })
    }
}


</script>

<style scoped>

</style>