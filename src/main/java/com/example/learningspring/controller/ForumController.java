package com.example.learningspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.learningspring.buisness.LoggedInUser;
import com.example.learningspring.buisness.NeedsAuth;
import com.example.learningspring.controller.binding.AddPostForm;
import com.example.learningspring.controller.exception.ResourceNotFoundException;
import com.example.learningspring.entity.CommentRecord;
import com.example.learningspring.entity.LikeId;
import com.example.learningspring.entity.LikeRecord;
import com.example.learningspring.entity.Post;
import com.example.learningspring.entity.User;
import com.example.learningspring.repository.CommentCRUDRepository;
import com.example.learningspring.repository.LikeCRUDRepository;
import com.example.learningspring.repository.LikeCountRepository;
import com.example.learningspring.repository.PostRepository;
import com.example.learningspring.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;

@Controller
@RequestMapping("/forum")
public class ForumController {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private PostRepository postRepository;
  
  @Autowired
  private LikeCountRepository likeCountRepository;
  
  @Autowired
  private LikeCRUDRepository likeCRUDRepository;
  
  
  @Autowired
  private CommentCRUDRepository commentCRUDRepository;
  
  @Autowired
  LoggedInUser loggedInUser;
  
  private List<User> userList;
  
  @PostConstruct
  public void init() {
    userList = new ArrayList<>();
  }
  
  @NeedsAuth(loginPage = "/loginpage")

  @GetMapping("/post/form")
  public String getPostForm(Model model) {
    model.addAttribute("postForm", new AddPostForm());
    userRepository.findAll().forEach(user -> userList.add(user));
    model.addAttribute("userList", userList);
    model.addAttribute("authorid", 0);
    return "forum/postForm";
  }
  
  
  @NeedsAuth(loginPage = "/loginpage")
  @PostMapping("/post/add")
  public String addNewPost(@ModelAttribute("postForm") AddPostForm postForm, BindingResult bindingResult, RedirectAttributes attr) throws ServletException {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getFieldErrors());
      attr.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
      attr.addFlashAttribute("post", postForm);
      return "redirect:/forum/post/form";
    }
    Optional<User> user = userRepository.findById(loggedInUser.getLoggedInUser().getId());
    if (user.isEmpty()) {
      throw new ServletException("Something went seriously wrong and we couldn't find the user in the DB");
    }
    Post post = new Post();
    post.setAuthor(user.get());
    post.setContent(postForm.getContent());
    postRepository.save(post);
    
    return String.format("redirect:/forum/post/%d", post.getId());
  }
  
  @NeedsAuth(loginPage = "/loginpage")
  @GetMapping("/post/{id}")
  public String postDetail(@PathVariable long id, Model model) throws ResourceNotFoundException {
	    Optional<Post> post = postRepository.findById(id);
	    if (post.isEmpty()) {
	      throw new ResourceNotFoundException("No post with the requested ID");
	    }
	    model.addAttribute("post", post.get());
	    model.addAttribute("userList", userList);
	    //int numLikes = likeCountRepository.countByPostId(id);
	    int numLikes = likeCRUDRepository.countByLikeIdPost(post.get());
	    int numComments = commentCRUDRepository.countByPost(post.get());
	    model.addAttribute("likeCount", numLikes);
	    model.addAttribute("commentCount", numComments);
	    return "forum/postDetail";
	  }
  
  @NeedsAuth(loginPage = "/loginpage")
  @PostMapping("/post/{id}/like")
  public String postLike(@PathVariable long id, RedirectAttributes attr) {
    LikeId likeId = new LikeId();
    likeId.setUser(userRepository.findById(loggedInUser.getLoggedInUser().getId()).get());
    likeId.setPost(postRepository.findById(id).get());
    LikeRecord like = new LikeRecord();
    like.setLikeId(likeId);
    likeCRUDRepository.save(like);
    return String.format("redirect:/forum/post/%d", id);   
  }
@PostMapping("/post/{id},{context}/comment")
public String postComment(@PathVariable Long id, Long commenterId, String content, RedirectAttributes attr) {
	  CommentRecord commentId = new CommentRecord();
	  commentId.setCommenter(userRepository.findById(loggedInUser.getLoggedInUser().getCommenterId()).get());
	  
	  
	  commentId.setPost(postRepository.findById(id).get());
	  commentCRUDRepository.save(commentId);
	  return String.format("redirect:/forum/post/%d", id);
}

  }

//public LikeCountRepository getLikeCountRepository() {
//	return likeCountRepository;
//}
//
//public void setLikeCountRepository(LikeCountRepository likeCountRepository) {
//	this.likeCountRepository = likeCountRepository;
//}
  
