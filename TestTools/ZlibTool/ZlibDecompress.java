package TestTools.ZlibTool;
//import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;


final public class ZlibDecompress {
    private static int DEFAULT_SIZE = 5;
    private static boolean DEFAULT_HEAD = false;

    private final int mSize;
    private final boolean mHead;

    private List<Inflater> mWorkingList;
    private List<Inflater> mCacheList;

    public ZlibDecompress() {
        this(DEFAULT_SIZE);
    }

    public ZlibDecompress(int size) {
        this(size, DEFAULT_HEAD);
    }


    public ZlibDecompress(int size, boolean head) {
        if (size <= 0) {
            size = DEFAULT_SIZE;
        }
        this.mSize = size;
        this.mHead = head;
        mWorkingList = Collections.synchronizedList(new LinkedList<Inflater>());
        mCacheList = Collections.synchronizedList(new LinkedList<Inflater>());
    }

    public byte[] decompress(byte[] compress) {
        Inflater inflater = getInflater();
        ByteArrayOutputStream bos = null;
        byte[] out = compress;
        try {
            inflater.reset();
            //���õ�ǰ�����ѹ
            inflater.setInput(compress, 0, compress.length);
            bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            while (!inflater.needsInput()) {
                int length = inflater.inflate(buf);
                bos.write(buf, 0, length);
            }
            out = bos.toByteArray();
        } catch (Exception ignore) {
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ignore) {
                }
            }
        }
        mCacheList.add(inflater);
        mWorkingList.remove(inflater);
        synchronized (ZlibDecompress.this) {
            try {
                ZlibDecompress.this.notifyAll();
            } catch (Exception ignore) {
            }
        }
        return out;
    }

    private Inflater getInflater() {
        if (mWorkingList.size() >= this.mSize) {
            try {
                ZlibDecompress.this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Inflater inflater;
        try {
            inflater = mCacheList.remove(0);
        } catch (IndexOutOfBoundsException exception) {
            inflater = new Inflater(mHead);
        } catch (Exception e) {
            inflater = new Inflater(mHead);
        }
        mWorkingList.add(inflater);
        return inflater;
    }
}