package com.example.cris.studentsapp.app.di;

import com.example.cris.studentsapp.screen.coursedetails.di.CourseDetailsModule;
import com.example.cris.studentsapp.screen.coursedetails.view.fragment.CourseDetailsFragment;
import com.example.cris.studentsapp.screen.courses.di.CoursesModule;
import com.example.cris.studentsapp.screen.courses.view.fragment.CoursesFragment;
import com.example.cris.studentsapp.screen.dashboard.di.DashboardModule;
import com.example.cris.studentsapp.screen.dashboard.view.fragment.DashboardFragment;
import com.example.cris.studentsapp.screen.deadlines.di.DeadlinesModule;
import com.example.cris.studentsapp.screen.deadlines.view.fragment.DeadlinesFragment;
import com.example.cris.studentsapp.screen.discussionslistperforum.di.DiscussionsPerForumModule;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.fragment.DiscussionsPerForumFragment;
import com.example.cris.studentsapp.screen.forumspercourse.di.ForumsModule;
import com.example.cris.studentsapp.screen.forumspercourse.view.fragment.ForumsFragment;
import com.example.cris.studentsapp.screen.messages.di.MessagesModule;
import com.example.cris.studentsapp.screen.messages.view.fragment.MessagesFragment;
import com.example.cris.studentsapp.screen.notifications.di.NotificationsModule;
import com.example.cris.studentsapp.screen.notifications.view.fragment.NotificationsFragment;
import com.example.cris.studentsapp.screen.profile.di.ProfileModule;
import com.example.cris.studentsapp.screen.profile.view.fragment.ProfileFragment;
import com.example.cris.studentsapp.screen.settings.di.SettingsModule;
import com.example.cris.studentsapp.screen.settings.view.fragment.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = {DashboardModule.class})
    abstract DashboardFragment buildDashboardFragment();

    @ContributesAndroidInjector(modules = {MessagesModule.class})
    abstract MessagesFragment buildMessagesFragment();

    @ContributesAndroidInjector(modules = {NotificationsModule.class})
    abstract NotificationsFragment buildNotificationsFragment();

    @ContributesAndroidInjector(modules = {SettingsModule.class})
    abstract SettingsFragment buildSettingsFragment();

    @ContributesAndroidInjector(modules = {ProfileModule.class})
    abstract ProfileFragment buildProfileFragment();

    @ContributesAndroidInjector(modules = {CoursesModule.class})
    abstract CoursesFragment buildCoursesFragment();

    @ContributesAndroidInjector(modules = {DeadlinesModule.class})
    abstract DeadlinesFragment buildDeadlinesFragment();

    @ContributesAndroidInjector(modules = {CourseDetailsModule.class})
    abstract CourseDetailsFragment buildCourseDetailsFragment();

    @ContributesAndroidInjector(modules = {ForumsModule.class})
    abstract ForumsFragment buildDiscussionsFragment();

    @ContributesAndroidInjector(modules = {DiscussionsPerForumModule.class})
    abstract DiscussionsPerForumFragment buildDiscussionsPerForumFragment();
}
