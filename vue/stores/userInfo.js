import { defineStore } from "pinia"; //导入状态管理器
import { ref } from "vue";
const useUserInfoStore = defineStore('userInfo', () => {
    //定义状态相关的内容

    const info = ref({})

    const setInfo = (newInfo) => {
        info.value = newInfo
    }


    const removeInfo = () => {
        info.value = {}
    }

    return { info, setInfo, removeInfo }
}, {
    persist: true
})

export default useUserInfoStore;