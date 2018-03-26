<?php


class Utils{
	
	var $file_contents;
	var $doc;
	
	public function __construct()
	{
	}
	
	function getSourceCode(){
		$context = stream_context_create(
		array(
			"http" => array(
				'header' => "User-Agent:MyAgent/1.0\r\n"
			)
		)
	);

	$this->file_contents = file_get_contents("http://le-multi-gagnant.over-blog.com/", false, $context);
	//echo htmlspecialchars($this->file_contents);
	}
	
	function loadDom(){
		libxml_use_internal_errors(true);
		$this->doc = new DOMDocument();
		
		if (!$this->doc->loadHTML($this->file_contents)) {
			libxml_clear_errors();
		}
	}
	
	function getURL(){
		$xpath = new DOMXpath($this->doc);
		$articles = $xpath->query("//article/header");

		if (!is_null($articles)) {
			foreach ($articles as $article) {
				$links = $article->getElementsByTagName('a');
				foreach ($links as $link) {
					$href = $link->getAttribute('href');
					echo htmlspecialchars($href).'</br>';
				}
			}
		}
	}
	
	
}
	
?>