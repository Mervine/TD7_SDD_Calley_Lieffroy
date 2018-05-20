<?php


class Utils{
	
	
	
	public function __construct()
	{
	}
	
	function getSourceCode($url){
		$context = stream_context_create(
		array(
			"http" => array(
				'header' => "User-Agent:MyAgent/1.0\r\n"
			)
		)
	);
	$file_contents = file_get_contents($url, false, $context);
	//$this->file_contents = file_get_contents("http://le-multi-gagnant.over-blog.com/", false, $context);
	//echo htmlspecialchars($this->file_contents);
	return $file_contents;
	}
	
	function loadDom($file_contents){
		libxml_use_internal_errors(true);
		$doc = new DOMDocument();
		
		if (!$doc->loadHTML($file_contents)) {
			libxml_clear_errors();
		}
		return $doc;
	}
	
	function getURL($doc){
		$xpath = new DOMXpath($doc);
		$articles = $xpath->query("//article/header");
		$listeArticles = array();
		$i = 0;
		if (!is_null($articles)) {
			foreach ($articles as $article) {
				$links = $article->getElementsByTagName('a');
				foreach ($links as $link) {
					$listeArticles[$i] = $link->getAttribute('href');
					$i++;
				}
			}
		}
		return $listeArticles;
	}
	
	function getCommentaires($site){
		$site_contents = $this->getSourceCode($site);
		$site_doc = $this->loadDom($site_contents);
		$listeURL = array();
		$listeURL = $this->getURL($site_doc);
		foreach ($listeURL as $url){
			$file_contents = $this->getSourceCode(htmlspecialchars($url));
			$doc = $this->loadDom($file_contents);
			$xpath = new DOMXpath($doc);
			$commentaires = $xpath->query("//div[@class=\'ob-comment\']//div[@class=\'ob-message\']");
			
			echo htmlspecialchars($url).'</br>';
			if (!is_null($commentaires)) {
				foreach ($commentaires as $com) {
					$test = $com->getElementsByTagName('span');
					echo htmlspecialchars($test).'</br>';
				}
			}
				
			
		}
	}
	
	
}
	
?>