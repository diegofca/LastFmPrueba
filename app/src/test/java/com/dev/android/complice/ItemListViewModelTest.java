/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dev.android.complice;

import android.content.Intent;
import android.databinding.Observable;
import android.test.mock.MockContext;
import com.dev.android.complice.data.MockView;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class ItemListViewModelTest {

    private static final String PEOPLE_CELL_TEST = "0177-6155420";
    private static final String PEOPLE_MAIL_TEST = "theodor.kaufmann@example.com";
    private static final String PEOPLE_PICTURE_TEST = "http://api.randomuser.me/portraits/women/39.jpg";
    private static final String PEOPLE_TITLE_TEST = "ms";
    private static final String PEOPLE_FIRST_TEST = "constance";
    private static final String PEOPLE_LAST_TEST = "fowler";

    @Mock private MockContext mockContext;

    @Test public void shouldGetPeopleCell() throws Exception {
        People people = new People();
        people.cell = PEOPLE_CELL_TEST;
        ItemListViewModel itemListViewModel = new ItemListViewModel(people, mockContext);
        assertEquals(people.cell, itemListViewModel.getCell());
    }

    @Test public void shouldGetPeopleMail() throws Exception {
        People people = new People();
        people.mail = PEOPLE_MAIL_TEST;
        ItemListViewModel itemListViewModel = new ItemListViewModel(people, mockContext);
        assertEquals(people.mail, itemListViewModel.getMail());
    }

    @Ignore public void shouldGetPeoplePicture() throws Exception {
        People people = new People();
        people.picture = Mockito.mock(Picture.class);
        people.picture.large = PEOPLE_PICTURE_TEST;
        ItemListViewModel itemListViewModel = new ItemListViewModel(people, mockContext);
        assertEquals(people.picture.large, itemListViewModel.getPictureProfile());
    }

    @Test public void shouldGetPeopleFullName() throws Exception {
        People people = new People();
        people.name = Mockito.mock(Name.class);
        people.name.title = PEOPLE_TITLE_TEST;
        people.name.firts = PEOPLE_FIRST_TEST;
        people.name.last = PEOPLE_LAST_TEST;
        people.fullName = people.name.title + "." + people.name.firts + " " + people.name.last;
        ItemListViewModel itemListViewModel = new ItemListViewModel(people, mockContext);
        assertEquals(people.fullName, itemListViewModel.getFullName());
    }

    @Test public void shouldStartPeopleDetailActivityOnItemClick() throws Exception {
        People people = new People();
        ItemListViewModel itemListViewModel = new ItemListViewModel(people, mockContext);
        itemListViewModel.onItemClick(new MockView(mockContext));
        verify(mockContext).startActivity(any(Intent.class));
    }

    @Test public void shouldNotifyPropertyChangeWhenSetPeople() throws Exception {
        People people = new People();
        ItemListViewModel itemListViewModel = new ItemListViewModel(people, mockContext);
        Observable.OnPropertyChangedCallback mockCallback = mock(Observable.OnPropertyChangedCallback.class);
        itemListViewModel.addOnPropertyChangedCallback(mockCallback);
        itemListViewModel.setPeople(people);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}
