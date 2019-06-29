package com.example.cris.studentsapp.app.di;

import com.example.cris.studentsapp.screen.adddiscussion.di.AddDiscussionModule;
import com.example.cris.studentsapp.screen.adddiscussion.model.AddDiscussionModel;
import com.example.cris.studentsapp.screen.adddiscussion.view.fragment.AddDiscussionFragment;
import com.example.cris.studentsapp.screen.coursedetails.di.CourseDetailsModule;
import com.example.cris.studentsapp.screen.coursedetails.view.fragment.CourseDetailsFragment;
import com.example.cris.studentsapp.screen.courses.di.CoursesModule;
import com.example.cris.studentsapp.screen.courses.view.fragment.CoursesFragment;
import com.example.cris.studentsapp.screen.dashboard.di.DashboardModule;
import com.example.cris.studentsapp.screen.dashboard.view.fragment.DashboardFragment;
import com.example.cris.studentsapp.screen.dayschedule.di.DayScheduleModule;
import com.example.cris.studentsapp.screen.dayschedule.view.fragment.DayScheduleFragment;
import com.example.cris.studentsapp.screen.deadlineassignment.di.DeadlineAssignmentsModule;
import com.example.cris.studentsapp.screen.deadlineassignment.view.fragment.DeadlineAssignmentsFragment;
import com.example.cris.studentsapp.screen.deadlines.di.DeadlinesModule;
import com.example.cris.studentsapp.screen.deadlines.view.fragment.DeadlinesFragment;
import com.example.cris.studentsapp.screen.discussionslistperforum.di.DiscussionsPerForumModule;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.fragment.DiscussionsPerForumFragment;
import com.example.cris.studentsapp.screen.forumspercourse.di.ForumsModule;
import com.example.cris.studentsapp.screen.forumspercourse.view.fragment.ForumsFragment;
import com.example.cris.studentsapp.screen.help.di.HelpModule;
import com.example.cris.studentsapp.screen.help.view.fragment.HelpFragment;
import com.example.cris.studentsapp.screen.notifications.di.NotificationsModule;
import com.example.cris.studentsapp.screen.notifications.view.fragment.NotificationsFragment;
import com.example.cris.studentsapp.screen.postsperdiscussion.di.PostsPerDiscussionModule;
import com.example.cris.studentsapp.screen.postsperdiscussion.view.fragment.PostsPerDiscussionFragment;
import com.example.cris.studentsapp.screen.profile.di.ProfileModule;
import com.example.cris.studentsapp.screen.profile.view.fragment.ProfileFragment;
import com.example.cris.studentsapp.screen.schedule.di.ScheduleModule;
import com.example.cris.studentsapp.screen.schedule.view.fragment.ScheduleFragment;
import com.example.cris.studentsapp.screen.settings.di.SettingsModule;
import com.example.cris.studentsapp.screen.settings.view.fragment.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = {DashboardModule.class})
    abstract DashboardFragment buildDashboardFragment();

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

    @ContributesAndroidInjector(modules = {DeadlineAssignmentsModule.class})
    abstract DeadlineAssignmentsFragment buildDeadlineAssignmentFragment();

    @ContributesAndroidInjector(modules = {ForumsModule.class})
    abstract ForumsFragment buildDiscussionsFragment();

    @ContributesAndroidInjector(modules = {DiscussionsPerForumModule.class})
    abstract DiscussionsPerForumFragment buildDiscussionsPerForumFragment();

    @ContributesAndroidInjector(modules = {AddDiscussionModule.class})
    abstract AddDiscussionFragment buildAddDiscussionFragment();

    @ContributesAndroidInjector(modules = {PostsPerDiscussionModule.class})
    abstract PostsPerDiscussionFragment buildPostsPerDiscussionFragment();

    @ContributesAndroidInjector(modules = {ScheduleModule.class})
    abstract ScheduleFragment buildScheduleFragment();

    @ContributesAndroidInjector(modules = {DayScheduleModule.class})
    abstract DayScheduleFragment buildDaySchedyleFragment();

    @ContributesAndroidInjector(modules = {HelpModule.class})
    abstract HelpFragment buildHelpFragment();


}
