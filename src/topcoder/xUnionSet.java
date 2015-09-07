package topcoder;

import java.util.*;

/**
 * Weighted Quick-Union With Path Compression
 *
 * @author Chauncey
 *
 */
public class xUnionSet
{
    private int count;
    private int[] id;
    private int[] size;

    public xUnionSet(int N) {
        this.count = N;
        this.id = new int[N];
        this.size = new int[N];

        for (int i = 0; i < this.count; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];  // 路径压缩，会破坏掉当前节点的父节点的尺寸信息，因为压缩后，当前节点的父节点已经变了
            p = id[p];
        }

        return p;
    }

    public void union(int p, int q) {
        int pCom = this.find(p);
        int qCom = this.find(q);

        if (pCom == qCom) {
            return;
        }
        // 按秩进行合并
        if (size[pCom] > size[qCom]) {
            id[qCom] = pCom;
            size[pCom] += size[qCom];
        } else {
            id[pCom] = qCom;
            size[qCom] += size[pCom];
        }
        // 每次合并之后，树的数量减1
        count--;
    }

    public int count() {
        return this.count;
    }
    public int size(int p) {
        return this.size[find(p)];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xUnionSet solution = new xUnionSet(100);
		solution.union(1,2);
		System.out.println(solution.find(1)+":"+solution.find(3)+":"+solution.count()+":"+solution.size(1));
		solution.union(2,3);
		System.out.println(solution.find(1)+":"+solution.find(3)+":"+solution.count()+":"+solution.size(1));
		solution.union(4,5);
		System.out.println(solution.find(1)+":"+solution.find(3)+":"+solution.count()+":"+solution.size(1));
		solution.union(5,15);
		System.out.println(solution.find(1)+":"+solution.find(3)+":"+solution.count()+":"+solution.size(1));
		solution.union(3,5);
		System.out.println(solution.find(1)+":"+solution.find(3)+":"+solution.count()+":"+solution.size(1));
	}

}
