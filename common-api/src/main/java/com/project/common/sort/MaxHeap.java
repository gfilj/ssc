package com.project.common.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data = new ArrayList<E>();

    public MaxHeap() {
    }

    public int size(){
        return data.size();
    }

    public void add(List<E> list){
        for(E e : list){
            add(e);
        }
    }
    public void add(Set<E> list){
        for(E e : list){
            add(e);
        }
    }
    public synchronized void add(E element) {
        if(data.contains(element)){
            replace(element);
            return;
        }
        data.add(element);

        int child = data.size() - 1;
        int parent = (child - 1) / 2;

        // 判断是否到达顶点
        while (child > 0) {
            // 父元素小于子元素，交换，保持父是大的
            if (data.get(parent).compareTo(element) < 0) {
                data.set(child, data.get(parent));
                data.set(parent, element);
                child = parent;
                parent = (child - 1) / 2;
            } else {
                // 已经是最大堆了，无需再比较
                break;
            }
        }
    }

    public synchronized E removeTop() {
        if (data.isEmpty())
            return null;

        E removed = data.get(0);

        // 因为一直交换的是最后的元素，这儿将其保存
        E last = data.get(data.size() - 1);
        data.set(0, last);
        data.remove(data.size() - 1);

        int parent = 0;
        int leftChild = parent * 2 + 1;
        int rightChild = parent * 2 + 2;

        while (leftChild <= data.size() - 1) {

            int maxIndex = leftChild;
            // 右子树存在，判断左右子树哪个小，保存坐标
            // 如果不存在，那么使用左子树的坐标
            // 保存大元素的坐标，可以省去考虑左右子树都存在，只有左存在的情况
            if (rightChild <= data.size() - 1) {
                if (data.get(rightChild).compareTo(data.get(leftChild)) > 0) {
                    maxIndex = rightChild;
                }
            }

            if (data.get(maxIndex).compareTo(last) > 0) {
                data.set(parent, data.get(maxIndex));
                data.set(maxIndex, last);
                parent = maxIndex;
                leftChild = parent * 2 + 1;
                rightChild = parent * 2 + 2;
            } else {
                break; // 已经达到了最小堆的性质
            }
        }

        return removed;
    }


    public synchronized void replace(E element) {
        int old = data.indexOf(element);
        if(element.compareTo(data.get(old))<0){
            return;
        }

        data.set(old, element);
        int parent = old/2;
        do{
            if (data.get(parent).compareTo(element) < 0) {
                data.set(old, data.get(parent));
                data.set(parent, element);
                old = parent;
                parent = old / 2;
            } else {
                // 已经是最大堆了，无需再比较
                break;
            }
        }while(old != 0);
    }

}

