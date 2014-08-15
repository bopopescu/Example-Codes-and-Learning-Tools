package com.bn.lccx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
public class CityAdapter<T> extends BaseAdapter implements Filterable
{
private List<T> mObjects;//�����W��  �~�r�Ʋ�
private List<T> mObjects2;//�����W��  �����Ʋ�
private final Object mLock = new Object();
private int mResource;//�i�ܼƲվA�t�����e��View Id
private int mDropDownResource;//�U�Ԯؤ����e��Id
private int mFieldId = 0;//�U�ԮؿﶵID
private boolean mNotifyOnChange = true;
private Context mContext;//��e�W�U��ﹳ - Activity
private ArrayList<T> mOriginalValues;//��l�ƲզC��
private ArrayFilter mFilter;
private LayoutInflater mInflater;
public CityAdapter(Context context, int textViewResourceId, T[] objects,T[] objects2) 
{
    init(context, textViewResourceId, 0, Arrays.asList(objects),Arrays.asList(objects2));
}
public void add(T object) 
{
    if (mOriginalValues != null) 
    {
        synchronized (mLock)
        {
            mOriginalValues.add(object);
            if (mNotifyOnChange) notifyDataSetChanged();
        }
    }
    else 
    {
        mObjects.add(object);
        if (mNotifyOnChange) notifyDataSetChanged();
    }
}
public void insert(T object, int index) 
{
    if (mOriginalValues != null)
    {
        synchronized (mLock) 
        {
            mOriginalValues.add(index, object);
            if (mNotifyOnChange) notifyDataSetChanged();
        }
    }
    else
    {
        mObjects.add(index, object);
        if (mNotifyOnChange) notifyDataSetChanged();
    }
}
public void remove(T object)
{
    if (mOriginalValues != null) 
    {
        synchronized (mLock) 
        {
            mOriginalValues.remove(object);
        }
    }
    else
    {
        mObjects.remove(object);
    }
    if (mNotifyOnChange) notifyDataSetChanged();
}
public void clear() //�q�C���R���Ҧ����H��
{
    if (mOriginalValues != null) 
    {
        synchronized (mLock) 
        {
            mOriginalValues.clear();
        }
    } 
    else
    {
        mObjects.clear();
    }
    if (mNotifyOnChange) notifyDataSetChanged();
}
public void sort(Comparator<? super T> comparator)//�ھګ��w���������A�t���������e�i��Ƨ�
{
    Collections.sort(mObjects, comparator);
    if (mNotifyOnChange) notifyDataSetChanged();        
}
@Override
public void notifyDataSetChanged()
{
    super.notifyDataSetChanged();
    mNotifyOnChange = true;
}
//�]�m�۰ʭק�
public void setNotifyOnChange(boolean notifyOnChange)
{
    mNotifyOnChange = notifyOnChange;
}
//�c�y��  -- ��l�ƩҦ��H��
private void init(Context context, int resource, int textViewResourceId, List<T> objects ,List<T> objects2) 
{
    mContext = context;
    mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mResource = mDropDownResource = resource;
    mObjects = objects;
    mObjects2 = objects2;
    mFieldId = textViewResourceId;
}
//��^�ƲվA�t�������p���W�U��ﹳ
public Context getContext()
{
    return mContext;
}
public int getCount() //��^  �����W�ٺ~�r  �C���j�p
{
    return mObjects.size();
}

public T getItem(int position)//��^�����W�ٺ~�r�C�����w��m���r�Ŧꪺ��
{
    return mObjects.get(position);
}
public int getPosition(T item)//��^ �����W�ٺ~�r �C�����w�� �r�Ŧ�� ������
{
    return mObjects.indexOf(item);
}
public long getItemId(int position)//�Nint����ƥH���㫬��^
{
    return position;
}
public View getView(int position, View convertView, ViewGroup parent)//�Ы�View
{
    return createViewFromResource(position, convertView, parent, mResource);
}
private View createViewFromResource(int position, View convertView, ViewGroup parent,//�Ы�View
        int resource)
{
    View view;
    TextView text;
    if (convertView == null) //�p�G��e����
    {
        view = mInflater.inflate(resource, parent, false);
    } 
    else //�p�G������
    {
        view = convertView;
    }

    try {
        if (mFieldId == 0) //�p�G��e�쬰��,���w�Ҧ����귽�N�O�@��TextView
        {
            text = (TextView) view;
        }
        else//�_�h,�b�ɭ������TextView
        {
            text = (TextView) view.findViewById(mFieldId);
        }
    } 
    catch (ClassCastException e) //���`�B�z
    {
        throw new IllegalStateException
        (
           "ArrayAdapter requires the resource ID to be a TextView", e
        );
    }
    text.setText(getItem(position).toString());//��Text�]��  -��^��e�����W�ٺ~�r�C���襤���� 
    return view;
}
public void setDropDownViewResource(int resource) //�ЫؤU�Ե���
{
    this.mDropDownResource = resource;
}
@Override
public View getDropDownView(int position, View convertView, ViewGroup parent) 
{
    return createViewFromResource(position, convertView, parent, mDropDownResource);
}
public static ArrayAdapter<CharSequence> createFromResource(Context context,//�q�~���귽���Ыطs���ƲվA�t��
        int textArrayResId, int textViewResId) 
{
    CharSequence[] strings = context.getResources().getTextArray(textArrayResId);//�Ыئr�ųЧǦC
    return new ArrayAdapter<CharSequence>(context, textViewResId, strings);//��^�ƲվA�t��
}
public Filter getFilter() //�o��L�o��
{
    if (mFilter == null)//�p�G����,�ЫؼƲչL�o��
    {
        mFilter = new ArrayFilter();
    }
    return mFilter;
}
//�ƲչL�o������ƲվA�t���H���w���e��}�Y,�p�G�򴣨Ѫ��e�󤣤ǰt,�h�N��q���R��
private class ArrayFilter extends Filter 
{
    @Override
    protected FilterResults performFiltering(CharSequence prefix)//����L�o
    {
        FilterResults results = new FilterResults();//�Ы�FilterResults�ﹳ
        if (mOriginalValues == null) //�p�G����
        {
            synchronized (mLock)
            {
                mOriginalValues = new ArrayList<T>(mObjects);
            }
        }
        if (prefix == null || prefix.length() == 0) 
        {
            synchronized (mLock) 
            {
                ArrayList<T> list = new ArrayList<T>(mOriginalValues);
                results.values = list;
                results.count = list.size();
            }
        } 
        else 
        {
            String prefixString = prefix.toString().toLowerCase();//�ഫ���p�g
            final ArrayList<T> values = mOriginalValues;
            final int count = values.size();
            final ArrayList<T> newValues = new ArrayList<T>(count);
            for (int i = 0; i < count; i++)
            {
                final T value = values.get(i);
                final String valueText = value.toString().toLowerCase();

                final T value2 = mObjects2.get(i);
                final String valueText2 = value2.toString().toLowerCase();
                
                //�d����� 
                if(valueText2.startsWith(prefixString))
                {
                        newValues.add(value);
                }//�d��~�r       
                else if(valueText.startsWith(prefixString))
                {
                        newValues.add(value);
                }
                else
                {      //�K�[�~�r���p
                        final String[] words = valueText.split(" ");
                        final int wordCount = words.length;
                        for (int k = 0; k < wordCount; k++) 
                        {
	                        if (words[k].startsWith(prefixString)) 
	                        {
	                            newValues.add(value);
	                            break;
	                        }
                        }
                        //�K�[�������p�~�r
                        final String[] words2 = valueText2.split(" ");
                        final int wordCount2 = words2.length;

                    for (int k = 0; k < wordCount2; k++) {
                        if (words2[k].startsWith(prefixString))
                        {
                            newValues.add(value);
                            break;
                        }
                    }  
                }
            }
            results.values = newValues;
            results.count = newValues.size();
        }
        return results;
    }
    @SuppressWarnings("unchecked")
	protected void publishResults(CharSequence constraint, FilterResults results) 
    {    
        mObjects = (List<T>) results.values;
        if (results.count > 0)
        {
            notifyDataSetChanged();
        } else
        {
            notifyDataSetInvalidated();
        }
    }
}
}
