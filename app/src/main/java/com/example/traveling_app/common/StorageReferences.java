package com.example.traveling_app.common;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageReferences {
    private static final FirebaseStorage INSTANCE = FirebaseStorage.getInstance("gs://traveling-app-7d1f0.appspot.com");
    public static final StorageReference POST_IMAGE_STORAGE_REF = INSTANCE.getReference("/images/posts/");
    public static final StorageReference USER_AVATAR_PICTURES = INSTANCE.getReference("/images/avatar-pictures");
}
