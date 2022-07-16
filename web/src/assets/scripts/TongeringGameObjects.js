const TONGERING_GAME_OBJECTS = [];//存储所有游戏对象


export class TongeringGameObject {
    constructor() {
        TONGERING_GAME_OBJECTS.push(this);
        this.timedelta = 0;//时间间隔
        this.has_called_start = false;//函数是否执行过
    }

    start() {//只执行一次
        
    }

    update() {//每一帧执行一次，除了第一帧
        
    }

    on_destroy() {//删除之前执行
        
    }

    destroy() {//清空数组
        for (let i in TONGERING_GAME_OBJECTS) {
            const obj = TONGERING_GAME_OBJECTS[i];
            if (obj == this) {
                TONGERING_GAME_OBJECTS.splice(i);
                break;
            }
        }
    }
}

let last_timestamp;//上一次执行时间

const step = timestamp => {
    for (let obj of TONGERING_GAME_OBJECTS) {
        if (!obj.has_called_start) {//没执行过
            obj.has_called_start = true;
            obj.start();
        }
        else {//执行过
            obj.timedelta = timestamp - last_timestamp;
            obj.update();
        }
    }
    last_timestamp = timestamp;

    requestAnimationFrame(step);
}

requestAnimationFrame(step);//在下一帧开始前，执行一下step函数，从而确保每一帧都会执行该step函数