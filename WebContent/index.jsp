<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="lab2.Point"%>
<%@ page import="java.lang.Math.*"%>
<%!
public static String getArrayString(String[] items){
    String result = "[";
    for(int i = 0; i < items.length; i++) {
        result += items[i];
        if(i < items.length - 1) {
            result += ", ";
        }
    }
    result += "]";

    return result;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab2</title>
<style>
@import url('style.css');
</style>
</head>
<body>
	<script type="text/javascript" src="script.js">
	</script>
	<form method="post" name="form" action="ControllerServlet"
		onsubmit="return validate()">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<th width="20%">Laboratory work 2</th>
				<th width="50%">Made by Vladimir Smirnov</th>
				<th width="15%">Group P3200</th>
				<th width="15%">Variant 7612</th>
			</tr>
		</table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<p>
									Enter the X variable:<span style="color: rgb(230, 0, 92)" id="Xwarning"></span>
								</p>
								<table>
									<tr>
										<td><p>
												<input name="X" type="radio" value="-2.0">-2
											</p></td>
										<td><p>
												<input name="X" type="radio" value="-1.5">-1.5
											</p></td>
										<td><p>
												<input name="X" type="radio" value="-1.0">-1
											</p></td>
										<td><p>
												<input name="X" type="radio" value="-0.5">-0.5
											</p></td>
										<td><p>
												<input name="X" type="radio" value="0">0
											</p></td>
										<td><p>
												<input name="X" type="radio" value="0.5">0.5
											</p></td>
										<td><p>
												<input name="X" type="radio" value="1.0">1
											</p></td>
										<td><p>
												<input name="X" type="radio" value="1.5">1.5
											</p></td>
										<td><p>
												<input name="X" type="radio" value="2.0">-2
											</p></td>
										<td><input name="X" type="hidden" id="hiddenX" value="">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<p>Enter the Y variable:</p>
							</td>
						</tr>
						<tr>
							<td><p><input type="text" name="Y" id="hiddenY" value="" /></p>
								<p>
									<span style="color: rgb(230, 0, 92)" id="Ywarning"></span>
								</p></td>
						</tr>
						<tr>
							<td><p>
									Enter the R variable:<span style="color: rgb(230, 0, 92)" id="Rwarning"></span>
								</p></td>
						</tr>
						<tr>
							<td>
								<p>
									<select size="1" name="R">
										<option disabled>Choose a radius</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</p>
							</td>
						</tr>
						<tr>
							<td><div class="centered">
									<input type="submit" class="button" />
								</div></td>
						</tr>
					</table>
				</td>
				<td>
					<table>
						<tr>
							<td>
								<div id="images">
									<canvas
										style="margin: 0; padding: 0; position: relative; top: 50px;"
										id="imgCanvas" width="320" height="320" onclick="draw(event)"></canvas>
									<%
										LinkedList<Point> points = new LinkedList<Point>();
										points = (LinkedList<Point>) session.getAttribute("points");
										if (points != null) {
											int size = points.size();
											long[] posx = new long[points.size()];
											long[] posy = new long[points.size()];
											String[] strposx = new String[points.size()];
											String[] strposy = new String[points.size()];
											String[] hits = new String[points.size()];
											for (int i = 0; i < points.size(); i++) {
												double x = points.get(i).getX();
												double y = points.get(i).getY();
												double r = points.get(i).getR();
												hits[i] = String.valueOf(points.get(i).getHit());
												posx[i] = Math.round(x * 160 / r + 160);
												posy[i] = Math.round(-y * 160 / r + 160);
												strposx[i] = String.valueOf(posx[i]);
												strposy[i] = String.valueOf(posy[i]);
											}
											String posxArr = getArrayString(strposx);
											String posyArr = getArrayString(strposy);
											String hitsArr = getArrayString(hits);
											
									%>
									<script type="text/javascript">
									var size = <%=size%>
									var posx = <%=posxArr%>;
									var posy = <%=posyArr%>;
									var hits = <%=hitsArr%>
									img.src = 'areas.png';
									img.onload = function() {
										draw_image(img);
										for (var i = 0; i<size; i++) {
											if (hits[i]) {
											draw_dote(posx[i], posy[i], "green");
											} else {
											draw_dote(posx[i], posy[i], "red");
											}
										}
									}	
									</script>
									<%	
										} else {
									%>
									<script type="text/javascript">
									img.src = 'areas.png';
									img.onload = function() {
										draw_image(img);
									}	
									</script>
									
									<% }; %>

									<span
										style="color: rgb(230, 0, 92); margin: 0; padding: 0; position: relative; top: 50px;"
										id="canvasWarning"></span>
								</div>
							</td>
						</tr>
					</table>
				</td>
		</table>
	</form>
</body>
</html>