package us.sts.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    Set<GroupData> delegate;

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }


    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }


    public Groups without(GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }


    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }


}
