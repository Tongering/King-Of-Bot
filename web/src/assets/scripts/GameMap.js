import { TongeringGameObject } from "@/assets/scripts/TongeringGameObjects";
import { Wall } from "@/assets/scripts/Wall";

export class GameMap extends TongeringGameObject {
    constructor(ctx, parent) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;

        this.rows = 13;
        this.cols = 13;
        
        this.inner_walls_count = 20;
        this.walls = [];
    }

    check_connectivity(g, sx, sy, tx, ty) {
        if (sx == tx && sy == ty) return true;
        g[sx][sy] = true;

        let dx = [-1, 0, 1, 0], dy = [0, 1, 0 - 1];
        for (let i = 0; i < 4; i++){
            let x = sx + dx[i], y = sy + dy[i];
            if (!g[x][y] || this.check_connectivity(g, x, y, tx, ty)) {
                return true;
            }
        }

        return false;
    }

    create_walls() {
        const g = [];

        //初始化数组
        for (let r = 0; r < this.rows; r++){
            g[r] = [];
            for (let c = 0; c < this.cols; c++){
                g[r][c] = false;
            }
        }

        //建立围墙
        for (let r = 0; r < this.rows; r++){
            g[r][0] = g[r][this.cols - 1] = true;
        }

        //建立围墙
        for (let c = 0; c < this.cols; c++){
            g[0][c] = g[this.rows - 1][c] = true;
        }

        //随机围墙
        for (let i = 0; i < this.inner_walls_count / 2; i++){
            for (let j = 0; j < 1000; j++){
                let r = parseInt(Math.random() * this.rows);
                let c = parseInt(Math.random() * this.cols);

                if (g[r][c] || g[c][r]) continue;
                if (r == 1 && c == this.cols - 2 || r == this.rows - 2 && c == 1) continue;
                g[r][c] = g[c][r] = true;
                break;
            }
        }

        const copy_g = JSON.parse(JSON.stringify(g));

        if (!this.check_connectivity(copy_g, this.rows - 2, 1, 1, this.cols - 2)) return false;

        //绘制围墙
        for (let r = 0; r < this.rows; r++){
            for (let c = 0; c < this.cols; c++){
                if (g[r][c]) {
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }

        return true;
    }

    start() {
        for (let i = 0; i < 1000; i++){
            if (this.create_walls()) {
                break;
            }
        }
        
    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    update() {
        this.update_size();
        this.render();
    }

    render() {//画到地图上
        const color_even = "#AAD751", color_odd = "#A2D048";

        for (let r = 0; r < this.rows; r++){
            for (let c = 0; c < this.cols; c++){
                if ((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even;
                }
                else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }

    }
}