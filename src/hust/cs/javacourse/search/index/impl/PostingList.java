package hust.cs.javacourse.search.index.impl;
import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;
import hust.cs.javacourse.search.index.FileSerializable;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*<pre>
* PostingList对象担包含了一个单词的Posting列表
* 必须实现下面接口
* FileSerializable:可序列化到文件从文件反序列化
* </pre>*/
public class PostingList extends AbstractPostingList implements FileSerializable{
    public PostingList(){//缺省构造函数
    }
    /*返回PostingList的字符串表示*/

    @Override
    public String toString() {
        return "PostingList{"+"List="+list+'}';
    }
    /*添加Posting，要求不能有内容重复的的Posting
    * */

    @Override
    public void add(AbstractPosting posting) {
        if(!this.contains(posting)){
            this.list.add(posting);
        }
    }
/*添加Posting列表，要求不能有内容重复的posting*/
    @Override
    public void add(List<AbstractPosting> postings) {
        for(AbstractPosting temp:postings){
            if(!this.list.contains(temp)){
                this.list.add(temp);
            }
        }
    }
/*返回指定下标位置的Posting*/
    @Override
    public AbstractPosting get(int index) {
        return list.get(index);
    }
/*返回指定Posting的下标*/
    @Override
    public int indexOf(AbstractPosting posting) {
        return list.indexOf(posting);
    }
    /*返回指定文档id的Posting对象的下标
     *
             * @param docId ：文档id
     * @return ：如果找到返回对应下标；否则返回-1*/
    @Override
    public int indexOf(int docId) {
        for(int i=0;i<this.list.size();i++){
            if(this.list.get(i).getDocId()==docId){
                return i;
            }
        }return -1;
    }
    /**
     * 是否包含指定Posting对象
     *
     * @param posting： 指定的Posting对象
     * @return : 如果包含返回true，否则返回false
     */
    @Override
    public boolean contains(AbstractPosting posting) {
        for(int i=0;i<this.list.size();i++){
            if(this.list.get(i).getDocId()==posting.getDocId()){
                return this.list.get(i).equals(posting);
            }
        }return false;
    }
/*删除指定下标的Posting对象*/
    @Override
    public void remove(int index) {
        if(index>=0&&index<this.list.size()){
            this.list.remove(index);
        }
    }
/*删除指定的Posting对象*/
    @Override
    public void remove(AbstractPosting posting) {
        if(!this.list.remove(posting)){
            System.out.println("remove fail");
        }
    }
/*返回PostingList的大小，个数*/
    @Override
    public int size() {
        return this.list.size();
    }
/*清楚PostingList*/
    @Override
    public void clear() {
        this.list.clear();
    }
//判空操作
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
/*根据文档id的大小对PostingList进行从小到大排序*/
    @Override
    public void sort() {
        this.list.sort(Comparator.naturalOrder());
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try{
            out.writeObject(this.list);
        }catch(IOException err){
            err.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try{
            this.list=(List<AbstractPosting>) (in.readObject());
        }catch(IOException |ClassNotFoundException err){
            System.out.println("ERROR:"+err);
        }
    }
}
