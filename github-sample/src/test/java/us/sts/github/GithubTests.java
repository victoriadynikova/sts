package us.sts.github;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSortedMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("b82b2dd597c3b4a6f3896f142fd2d64ecb766846");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("victoriadynikova", "sts")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableBiMap.Builder<String,String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
